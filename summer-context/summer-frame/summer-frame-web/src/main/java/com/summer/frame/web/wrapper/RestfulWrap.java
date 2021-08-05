package com.summer.frame.web.wrapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.summer.frame.commons.exception.CustomizeException;
import com.summer.frame.commons.wrappper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>RESTFul Response封装</p>
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-26
 */
@ControllerAdvice
public class RestfulWrap extends BasicErrorController implements ResponseBodyAdvice<Object> {

    /**
     * 无需封装输出路径
     */
    private static final List<String> NOT_WRAPPER_URI = Arrays.asList(
            "/error",
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/configuration/ui",
            "/swagger-resources/configuration/security"
    );

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

    /**
     * 异常捕捉, 针对404
     *
     * @param httpServletRequest {@link HttpServletRequest}
     * @return 404 Wrapper封装
     * @author Tiny Chiang
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest httpServletRequest) {
        HttpStatus status = super.getStatus(httpServletRequest);
        Wrapper<Object> wrapper = Wrapper.instance().error(status.value(), status.getReasonPhrase());
        String wrapperJson = JSON.toJSONString(wrapper, SerializerFeature.WriteMapNullValue);
        Map<String, Object> map = (Map<String, Object>) JSON.parseObject(wrapperJson, Map.class);
        return new ResponseEntity<>(map, status);
    }

    @Override
    public boolean supports(@NonNull MethodParameter methodParameter, @NonNull Class<? extends HttpMessageConverter<?>> clazz) {
        return Boolean.TRUE;
    }

    @Override
    public Object beforeBodyWrite(Object object, @NonNull MethodParameter methodParameter, @NonNull MediaType mediaType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> clazz, @NonNull ServerHttpRequest serverHttpRequest,
                                  @NonNull ServerHttpResponse serverHttpResponse) {
        return object instanceof Wrapper || NOT_WRAPPER_URI.contains(serverHttpRequest.getURI().getPath()) ?
                object : Wrapper.instance().success(object);
    }

    @Autowired
    public RestfulWrap(ErrorAttributes errorAttributes) {
        super(errorAttributes, new ErrorProperties());
    }
}