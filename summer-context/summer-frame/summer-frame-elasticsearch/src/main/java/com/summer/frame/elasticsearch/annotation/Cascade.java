package com.summer.frame.elasticsearch.annotation;

import com.summer.frame.elasticsearch.enums.Link;

import java.lang.annotation.*;

/**
 * 级联检索
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-28
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cascade {

    /**
     * 级联字段
     */
    String[] fields();

    /**
     * 条件连接方式
     *
     * @return 默认 MUST
     */
    Link link() default Link.MUST;

}