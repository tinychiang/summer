package com.summer.biz;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author tianyi.jiang
 * @version 1.0.0
 * @date 2021-07-09
 */
@EnableAsync
@SpringBootApplication
public class Application {

    @Async
    public void testAsync(){

    }

}