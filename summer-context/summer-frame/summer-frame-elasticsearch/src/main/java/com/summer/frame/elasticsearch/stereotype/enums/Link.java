package com.summer.frame.elasticsearch.stereotype.enums;

/**
 * 连接条件
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-28
 */
public enum Link {
    /**
     * 满足全部
     */
    MUST,
    /**
     * 满足其中之一
     */
    SHOULD
}