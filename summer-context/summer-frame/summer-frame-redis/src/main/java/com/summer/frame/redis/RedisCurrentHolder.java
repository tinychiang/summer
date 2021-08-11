package com.summer.frame.redis;

import com.summer.frame.commons.AbstractCurrentHolder;
import com.summer.frame.commons.AbstractCurrentUser;
import com.summer.frame.commons.exception.CustomizeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
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
public class RedisCurrentHolder<T extends AbstractCurrentUser> extends AbstractCurrentHolder<T> {

    private final RedisTemplate<String, Object> redisTemplate;

    @SuppressWarnings("unchecked")
    @Override
    public T currentUser() {
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String token = httpServletRequest.getHeader(TOKEN);
        if (StringUtils.isNotEmpty(token)) {
            Object object = redisTemplate.boundValueOps(token).get();
            if (object != null) {
                return (T) object;
            }
        }
        throw new CustomizeException(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase());
    }

    public RedisCurrentHolder(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}