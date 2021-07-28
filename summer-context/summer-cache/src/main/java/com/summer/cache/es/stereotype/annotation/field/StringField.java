package com.summer.cache.es.stereotype.annotation.field;

import com.summer.cache.es.stereotype.enums.Link;
import org.elasticsearch.index.query.Operator;

import java.lang.annotation.*;

/**
 * 字符串检索
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-28
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StringField {

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
     * 条件组合方式
     *
     * @return 默认 AND
     */
    Operator operator() default Operator.AND;

    /**
     * 是否格式化查询参数
     *
     * @return 默认 false
     */
    boolean escape() default false;

}