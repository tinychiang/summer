package com.summer.frame.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.summer.frame.wrap.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>异常捕捉, 针对404</p>
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-26
 */
@RestController
public class CustomizeErrorController extends BasicErrorController {

    @SuppressWarnings("all")
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        HttpStatus status = super.getStatus(request);
        Wrapper<Object> wrapper = Wrapper.instance().error(status.value(), status.getReasonPhrase());
        String wrapperJson = JSON.toJSONString(wrapper, SerializerFeature.WriteMapNullValue);
        Map<String, Object> map = (Map<String, Object>) JSON.parseObject(wrapperJson, Map.class);
        return new ResponseEntity<>(map, status);
    }

    @Autowired
    public CustomizeErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes, new ErrorProperties());
    }

}