package com.summer.frame.elasticsearch.annotation;

import java.lang.annotation.*;

/**
 * 聚合检索
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-30
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Aggregation {

    /**
     * 是否开启
     *
     * @return 默认 false
     */
    boolean enable() default false;

}