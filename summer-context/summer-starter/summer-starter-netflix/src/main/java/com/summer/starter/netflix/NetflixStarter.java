package com.summer.starter.netflix;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * <p>Spring Cloud Netflix</p>
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-23
 */
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(basePackages = {"com.summer.db.mysql.mapper"})
@SpringBootApplication(scanBasePackages = {"com.summer.**"})
@EnableElasticsearchRepositories(basePackages = {"com.summer.db.elasticsearch.repository"})
public class NetflixStarter {

    public static void main(String[] args) {
        SpringApplication.run(NetflixStarter.class, args);
    }

}