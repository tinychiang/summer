package com.summer.cache.es.stereotype.annotation;

import java.lang.annotation.*;

/**
 * 级联检索
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-28
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Cascade {

    /**
     * 级联字段数组
     */
    String[] fields();

}