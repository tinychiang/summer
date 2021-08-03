package com.summer.frame.redis.lock.annotation;

import java.lang.annotation.*;

/**
 * 锁
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-08-03
 */
@Target(value = {ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DistributedLock {

    /**
     * 锁名称
     */
    String name();

    /**
     * 释放时长
     *
     * @return 默认60秒
     */
    long release() default 60000;

}