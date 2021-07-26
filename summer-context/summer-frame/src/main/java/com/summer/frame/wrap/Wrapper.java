package com.summer.frame.wrap;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * <p>RESTFul API外壳</p>
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-26
 */
@Data
public class Wrapper<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private int code;
    /**
     * 主体
     */
    private T content;
    /**
     * 描述
     */
    private String message;

    private Wrapper() {
    }

    public static Wrapper<Object> instance() {
        return new Wrapper<>();
    }

    public Wrapper<T> success(T content) {
        this.code = HttpStatus.OK.value();
        this.content = content;
        return this;
    }

    public Wrapper<T> error(int code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

}