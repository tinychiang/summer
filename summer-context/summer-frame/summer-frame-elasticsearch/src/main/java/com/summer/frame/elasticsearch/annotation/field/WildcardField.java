package com.summer.frame.elasticsearch.annotation.field;

import com.summer.frame.elasticsearch.enums.Link;

import java.lang.annotation.*;

/**
 * 模糊检索
 *
 * @author tianyi.jiang
 * @version 1.0.0
 * @date 2021-07-28
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WildcardField {

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

    /**
     * "*" 前缀
     *
     * @return 默认 false
     */
    boolean enablePrefix() default false;

    /**
     * "*" 后缀
     *
     * @return 默认 false
     */
    boolean enableSuffix() default false;

}
