package com.summer.cache.es.stereotype.annotation.field;

import com.summer.cache.es.stereotype.enums.Link;

import java.lang.annotation.*;

/**
 * 模糊检索
 *
 * @author tianyi.jiang
 * @version 1.4.1
 * @date 2021-05-13
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
