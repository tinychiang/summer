package com.summer.frame.elasticsearch;

import org.springframework.data.elasticsearch.core.SearchHits;

/**
 * 检索、聚合分析等
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-28
 */
public interface ElasticsearchAdapter {

    /**
     * 分页 / 排序 / 检索 / 聚合分析, 并指定返回类型
     *
     * @param condition 条件
     * @param target    转换目标类
     * @param <T>       条件类型, 继承分页助手
     * @param <E>       返回类型
     * @return 结果集
     * @author Tiny Chiang
     * @since 1.0.0
     */
    <T, E> SearchHits<E> search(T condition, Class<E> target);

    /**
     * 游标检索
     *
     * @param condition 条件
     * @param target    转换目标类
     * @param <T>       条件类型, 继承分页助手
     * @param <E>       返回类型
     * @return 结果集
     * @author Tiny Chiang
     * @since 1.0.0
     */
    <T extends AbstractPageHelper, E> SearchHits<E> scrollSearch(T condition, Class<E> target);

}