package com.summer.db.mysql;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Mybatis Plus 配置
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-08-04
 */
@Configuration
@EnableTransactionManagement
public class MybatisPlusConfiguration {

    /**
     * 分页
     *
     * @return MybatisPlusInterceptor
     * @author Tiny Chiang
     * @since 1.0.0
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return mybatisPlusInterceptor;
    }

    /**
     * 一 / 二级缓存遵循MybatisPlus规则
     *
     * @return ConfigurationCustomizer
     * @author Tiny Chiang
     * @since 1.0.0
     */
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configurationCustomizer -> configurationCustomizer.setUseDeprecatedExecutor(false);
    }

}