package com.summer.frame.elasticsearch.enums;

/**
 * 统计类型
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-08-02
 */
public enum AggregationType {
    /**
     * 均值
     */
    AVG,
    /**
     * 数量统计
     */
    COUNT,
    /**
     * 时间统计
     */
    DATE_HISTOGRAM,
    /**
     * 最大值
     */
    MAX,
    /**
     * 最小值
     */
    MIN,
    /**
     * 求和
     */
    SUM;
}