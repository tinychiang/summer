package com.summer.biz.controller;

import com.summer.frame.exception.CustomizeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>异常 - 控制器</p>
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-23
 */
@Api(value = "自定异常测试")
@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @ApiOperation(value = "异常")
    @GetMapping
    public void exception() {
        throw new CustomizeException(5001, "customize exception");
    }

}