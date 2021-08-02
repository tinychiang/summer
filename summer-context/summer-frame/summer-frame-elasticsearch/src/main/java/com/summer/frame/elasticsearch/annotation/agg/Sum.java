package com.summer.frame.elasticsearch.annotation.agg;

import java.lang.annotation.*;

/**
 * 求和
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-08-02
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Sum {

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