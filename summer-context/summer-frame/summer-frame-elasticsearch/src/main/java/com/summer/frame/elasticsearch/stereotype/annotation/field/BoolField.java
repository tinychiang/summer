package com.summer.frame.elasticsearch.stereotype.annotation.field;


import com.summer.frame.elasticsearch.stereotype.enums.Link;

import java.lang.annotation.*;

/**
 * 属性
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-28
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BoolField {

    /**
     * 指定查询字段
     *
     * @return 默认属性名
     */
    String field() default "";

    /**
     * 条件连接方式
     *
     * @return 默认 MUST
     */
    Link link() default Link.MUST;

}