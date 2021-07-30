package com.summer.frame.elasticsearch.annotation;

import java.lang.annotation.*;

/**
 * 过滤 / 排除字段
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-28
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FetchSource {

    /**
     * 包含字段
     *
     * @return 默认空
     */
    String[] includes() default {};

    /**
     * 排除字段
     *
     * @return 默认空
     */
    String[] excludes() default {};

}
