package com.summer.frame.wrap;

import com.summer.frame.exception.CustomizeException;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Arrays;
import java.util.List;

/**
 * <p>RESTFul Response封装</p>
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-26
 */
@ControllerAdvice
public class RestWrap implements ResponseBodyAdvice<Object> {

    private static final List<String> NOT_WRAPPER_URI = Arrays.asList("/error", "/v2/api-docs",
            "/swagger-resources", "/swagger-resources/configuration/ui", "/swagger-resources/configuration/security");

    @ResponseBody
    @ExceptionHandler(CustomizeException.class)
    public Wrapper<Object> customizeExceptionHandler(CustomizeException customizeException) {
        return Wrapper.instance().error(customizeException.getCode(), customizeException.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Wrapper<Object> exceptionHandler(Exception exception) {
        return Wrapper.instance().error(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }

    @SuppressWarnings("all")
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> clazz) {
        return Boolean.TRUE;
    }

    @SuppressWarnings("all")
    @Override
    public Object beforeBodyWrite(Object object, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> clazz,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (object instanceof Wrapper || NOT_WRAPPER_URI.contains(serverHttpRequest.getURI().getPath())) {
            return object;
        }
        return Wrapper.instance().success(object);
    }
}