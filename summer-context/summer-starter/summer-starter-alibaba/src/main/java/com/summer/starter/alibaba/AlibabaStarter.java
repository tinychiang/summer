package com.summer.starter.alibaba;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * Spring Cloud Alibaba
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-23
 */
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.summer.db.mysql.mapper"})
@EnableElasticsearchRepositories(basePackages = {"com.summer.db.elasticsearch.repository"})
@ImportResource(locations = {"classpath:provider.xml", "classpath:consumer.xml"})
@SpringBootApplication(scanBasePackages = {"com.summer.**"})
public class AlibabaStarter {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaStarter.class, args);
    }

}