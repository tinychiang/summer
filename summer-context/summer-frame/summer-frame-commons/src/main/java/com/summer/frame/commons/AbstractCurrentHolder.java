package com.summer.frame.commons;

import java.io.Serializable;

/**
 * 管理器
 *
 * @param <T>
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-08-09
 */
public abstract class AbstractCurrentHolder<T extends AbstractCurrentHolder.User> {

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

    public static abstract class User implements Serializable {

        private static final long serialVersionUID = 1L;

        private String id;

        private String username;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}