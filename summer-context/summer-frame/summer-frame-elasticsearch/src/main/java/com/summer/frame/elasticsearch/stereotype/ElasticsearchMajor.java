package com.summer.frame.elasticsearch.stereotype;

import com.summer.frame.elasticsearch.AbstractPageHelper;

import java.util.List;
import java.util.Map;

/**
 * 条件筛选、聚合检索等
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-28
 */
public interface ElasticsearchMajor {

    /**
     * 分页、排序、条件聚合筛选统计
     *
     * @param condition 查询条件
     * @return 结果集
     * @author Tiny Chiang
     * @since 1.0.0
     */
    <S extends AbstractPageHelper> List<Map<String, Object>> search(S condition);

    /**
     * 分页、排序、条件聚合筛选统计
     *
     * @param condition 查询条件
     * @param clazz     转换目标类
     * @param <S>       入参类型
     * @param <T>       出参类型
     * @return 结果集
     * @author Tiny Chiang
     * @since 1.0.0
     */
    <S extends AbstractPageHelper, T> List<T> search(S condition, Class<T> clazz);

    /**
     * 游标查询
     *
     * @param scrollId 游标Id
     * @return 结果集
     * @author Tiny Chiang
     * @since 1.0.0
     */
    List<Map<String, Object>> searchByScroll(String scrollId);

    /**
     * 游标查询
     *
     * @param scrollId 游标Id
     * @param clazz    转换目标类
     * @param <T>      出参类型
     * @return 结果集
     * @author Tiny Chiang
     * @since 1.0.0
     */
    <T> List<T> searchByScroll(String scrollId, Class<T> clazz);

}