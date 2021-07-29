package com.summer.frame.elasticsearch.annotation;

import org.elasticsearch.action.search.SearchType;

import java.lang.annotation.*;

/**
 * ES查询 - 用于SearchRequestBuilder构建
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

    /**
     * <p>
     * QUERY_THEN_FETCH: 针对所有块查询, 结果被排序和分级, 返回结果不会重复(快)
     * QUERY_AND_FETCH: 所有相关的shard上执行检索并返回结果
     * DFS_QUERY_THEN_FETCH: 与QUERY_THEN_FETCH相同, 预期一个初始散射相伴用来更准确的score计算分配的term频率(慢)
     * </p>
     *
     * @return 默认 QUERY_THEN_FETCH
     */
    SearchType searchType() default SearchType.QUERY_THEN_FETCH;

    /**
     * 包含字段
     *
     * @return 默认空
     */
    String[] includes() default {};

    /**
     * 排除字段
     *
     * @return 默认空
     */
    String[] excludes() default {};

}