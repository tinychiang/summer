package com.summer.frame.elasticsearch.annotation.agg;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 数量统计
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-08-02
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Count {

    /**
     * 统计名称
     *
     * @return 默认无
     */
    String name() default "";

    /**
     * 统计字段
     */
    String field();

    /**
     * 是否存在父项
     *
     * @return 默认否
     */
    boolean superExist() default false;

    /**
     * 子统计名称
     *
     * @return 默认无
     */
    String subName() default "";

}