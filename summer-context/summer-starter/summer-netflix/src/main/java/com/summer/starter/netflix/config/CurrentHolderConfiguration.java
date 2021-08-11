package com.summer.starter.netflix.config;

import com.summer.frame.commons.AbstractCurrentHolder;
import com.summer.frame.commons.AbstractCurrentUser;
import com.summer.frame.redis.RedisCurrentHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 会话信息配置
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-08-11
 */
@Component
public class CurrentHolderConfiguration {

    @Bean
    public AbstractCurrentHolder<AbstractCurrentUser> abstractCurrentHolder(@Autowired RedisTemplate<String, Object> redisTemplate) {
        return new RedisCurrentHolder<>(redisTemplate);
    }

}