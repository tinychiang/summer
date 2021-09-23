package com.summer.frame.web;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 * Web MVC配置
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-26
 */
@Configuration
public class WebMvcConfigurer implements
        org.springframework.web.servlet.config.annotation.WebMvcConfigurer {

    /**
     * 跨域
     *
     * @param registry {@link CorsRegistry}
     * @author Tiny Chiang
     * @since 1.0.0
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/cors/**")
                .allowedHeaders("*")
                .allowedMethods("POST", "GET", "PUT", "DELETE")
                .allowedOrigins("*");
    }

    /**
     * 异常页面
     *
     * @return {@link WebServerFactoryCustomizer}
     * @author Tiny Chiang
     * @since 1.0.0
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return factory -> {
            ErrorPage error404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
            factory.addErrorPages(error404);
        };
    }

}