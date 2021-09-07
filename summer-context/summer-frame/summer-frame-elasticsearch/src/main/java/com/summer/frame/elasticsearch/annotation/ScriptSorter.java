package com.summer.frame.elasticsearch.annotation;

import org.elasticsearch.search.sort.ScriptSortBuilder;

import java.lang.annotation.*;

/**
 * 脚本排序
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-28
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ScriptSorter {

    /**
     * 排序脚本
     *
     * @return 默认空
     */
    String script();

    /**
     * 排序类型
     *
     * @return String
     */
    ScriptSortBuilder.ScriptSortType scriptSortType() default ScriptSortBuilder.ScriptSortType.STRING;

}