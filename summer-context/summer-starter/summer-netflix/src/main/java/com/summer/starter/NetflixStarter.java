package com.summer.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * <p>Spring Cloud Netflix</p>
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-23
 */
@EnableAsync
@SpringBootApplication(scanBasePackages = {"com.summer.**"})
public class NetflixStarter {

    public static void main(String[] args) {
        SpringApplication.run(NetflixStarter.class, args);
    }

}