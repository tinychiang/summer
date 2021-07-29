package com.summer.frame.elasticsearch.annotation;

import java.lang.annotation.*;

/**
 * 高亮检索
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-28
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Highlighters {

    Highlighter[] highlighters() default {};

}
