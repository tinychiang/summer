package com.summer.frame.commons;

/**
 * 管理器
 *
 * @param <T>
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-08-09
 */
public abstract class AbstractCurrentHolder<T> {

    protected static final String TOKEN = "token";

    /**
     * 当前用户
     *
     * @return 用户信息
     * @author Tiny chiang
     * @since 1.0.0
     */
    public T currentUser() {
        return null;
    }

}