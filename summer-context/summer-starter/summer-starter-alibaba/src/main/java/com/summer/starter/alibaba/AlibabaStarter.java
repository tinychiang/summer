package com.summer.starter.alibaba;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * <p>Spring Cloud Alibaba</p>
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-23
 */
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.summer.db.mysql.mapper"})
@SpringBootApplication(scanBasePackages = {"com.summer.**"})
@EnableElasticsearchRepositories(basePackages = {"com.summer.db.elasticsearch.repository"})
public class AlibabaStarter {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaStarter.class, args);
    }

}