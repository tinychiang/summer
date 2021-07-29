package com.summer.frame.elasticsearch.annotation;

import java.lang.annotation.*;

/**
 * 排序
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-28
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Sorter {

    /**
     * 排序脚本
     *
     * @return 默认空
     */
    String script() default "";

}