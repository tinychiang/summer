package com.summer.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Oauth2 认证、鉴权服务
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-09-23
 */
@EnableResourceServer
@SpringBootApplication
public class AuthorizationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }

}