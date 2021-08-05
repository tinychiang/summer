package com.summer.frame.commons.wrappper;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>RESTFul API封装</p>
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-26
 */
@Data
@ApiModel(value = "Wrapper")
public class Wrapper<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码")
    private int code;
    /**
     * 主体
     */
    @ApiModelProperty(value = "主体", allowEmptyValue = true)
    private T content;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", allowEmptyValue = true)
    private String message;

    private Wrapper() {
    }

    public static Wrapper<Object> instance() {
        return new Wrapper<>();
    }

    public Wrapper<T> success(T content) {
        this.code = 200;
        this.content = content;
        return this;
    }

    public Wrapper<T> error(int code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }
}