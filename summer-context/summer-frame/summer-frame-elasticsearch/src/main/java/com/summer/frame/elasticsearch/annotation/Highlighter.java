package com.summer.frame.elasticsearch.annotation;

import java.lang.annotation.*;

/**
 * 高亮检索
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-28
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Highlighter {

    /**
     * 指定高亮字段
     */
    String field();

    /**
     * 前缀
     */
    String[] preTags() default {};

    /**
     * 尾缀
     */
    String[] postTags() default {};

    /**
     * 高亮后显示长度
     *
     * @return 默认 -1
     */
    int fragmentSize() default -1;

    /**
     * 显示高亮片段
     *
     * @return 默认 0
     */
    int numOfFragments() default 0;

}