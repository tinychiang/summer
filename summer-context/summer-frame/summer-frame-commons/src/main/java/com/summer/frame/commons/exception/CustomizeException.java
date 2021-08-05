package com.summer.frame.commons.exception;

import lombok.Getter;

/**
 * <p>自定义异常</p>
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-26
 */
public class CustomizeException extends RuntimeException {

    @Getter
    private int code = 500;

    public CustomizeException() {
    }

    public CustomizeException(String message) {
        super(message);
    }

    public CustomizeException(int code, String message) {
        super(message);
        this.code = code;
    }

    public CustomizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomizeException(Throwable cause) {
        super(cause);
    }

    public CustomizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}