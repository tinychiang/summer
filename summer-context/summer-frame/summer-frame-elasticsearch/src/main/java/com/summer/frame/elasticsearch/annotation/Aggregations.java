package com.summer.frame.elasticsearch.annotation;

import com.summer.frame.elasticsearch.annotation.agg.*;

import java.lang.annotation.*;

/**
 * 聚合检索
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-30
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Aggregations {

    Avg[] avg() default {};

    Count[] count() default {};

    DateHistogram[] dateHistogram() default {};

    Max[] max() default {};

    Min[] min() default {};

    Sum[] sum() default {};

}