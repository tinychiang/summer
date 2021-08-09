package com.summer.frame.redis;

import com.summer.frame.commons.AbstractCurrentHolder;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Redis 会话管理
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-08-09
 */
@Component
public class RedisCurrentHolder extends AbstractCurrentHolder {

    private final RedisTemplate<String, Object> redisTemplate;

    @SuppressWarnings("unchecked")
    @Override
    public <T> T currentUser(Class<T> clazz) {
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String token = httpServletRequest.getHeader(TOKEN);
        if (StringUtils.isNotEmpty(token)) {
            Object object = redisTemplate.boundValueOps(token).get();
            return ObjectUtils.defaultIfNull((T) object, null);
        }
        return null;
    }

    @Autowired
    public RedisCurrentHolder(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}