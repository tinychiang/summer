package com.summer.frame.commons;

/**
 * 管理器
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-08-09
 */
public abstract class AbstractCurrentHolder {

    protected static final String TOKEN = "token";

    /**
     * 当前用户
     *
     * @param clazz 用户实体
     * @param <T>   实体类型
     * @return 用户信息
     * @author Tiny chiang
     * @since 1.0.0
     */
    public <T> T currentUser(Class<T> clazz) {
        return null;
    }

}