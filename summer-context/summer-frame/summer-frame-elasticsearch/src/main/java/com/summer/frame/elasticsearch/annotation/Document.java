package com.summer.frame.elasticsearch.annotation;

import org.elasticsearch.action.search.SearchType;

import java.lang.annotation.*;

/**
 * 条件主声明
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-28
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Document {

    /**
     * 索引
     *
     * @return 默认空, 查询所有
     */
    String[] indices();

    /**
     * 检索类型; 注: ES 7.x版本后去除type, 但兼容
     *
     * @return 默认空, 查询所有
     * @deprecated
     */
    String[] types() default {};

}