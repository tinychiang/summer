package com.summer.oauth2.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * 服务端鉴权配置
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-09-23
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                // 客户端 id/secret
                .withClient("webapp").secret("secret")
                // 授权妈模式
                .authorizedGrantTypes("authorization code")
                .scopes("user_info")
                //自动审批
                .autoApprove(true)
                //有效期1hour
                .accessTokenValiditySeconds(3600);
    }

}