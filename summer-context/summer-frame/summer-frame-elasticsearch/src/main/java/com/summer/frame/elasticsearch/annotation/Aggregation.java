package com.summer.frame.elasticsearch.annotation;

import com.summer.frame.elasticsearch.enums.AggregationType;

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
     * 名称
     */
    String name();

    /**
     * 统计字段
     */
    String field();

    /**
     * 类型
     */
    AggregationType type();

    /**
     * 根节点
     *
     * @return 默认否
     */
    boolean root() default true;

    /**
     * 统计子项名称
     *
     * @return 默认无
     */
    String subName() default "";

}