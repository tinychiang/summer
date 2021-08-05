package com.summer.frame.redis.lock;

import com.summer.frame.commons.exception.CustomizeException;
import com.summer.frame.redis.lock.annotation.DistributedLock;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-08-03
 */
@Aspect
@Lazy(value = false)
@Component
public class DistributedLockHandler {

    private static final int DISTRIBUTED_RESOURCE_COMPETE_CODE = 449;

    private final StringRedisTemplate stringRedisTemplate;

    @Around("@annotation(com.summer.frame.redis.lock.annotation.DistributedLock)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        DistributedLock distributedLock = methodSignature.getMethod().getAnnotation(DistributedLock.class);
        // 失效时刻时间戳
        long expectTimeout = Math.addExact(System.currentTimeMillis(), distributedLock.release());
        // 加锁
        Boolean lockable = stringRedisTemplate.opsForValue().setIfAbsent(distributedLock.name(), String.valueOf(expectTimeout), distributedLock.release(), TimeUnit.MILLISECONDS);
        // 加锁失败, 判断锁超时, 防止死锁
        if (BooleanUtils.isFalse(lockable)) {
            String lockCylinder = stringRedisTemplate.opsForValue().get(distributedLock.name());
            if (StringUtils.isNotEmpty(lockCylinder) && Long.parseLong(lockCylinder) < System.currentTimeMillis()) {
                // 锁超时, 解锁
                this.unlock(distributedLock.name());
            }
            throw new CustomizeException(DISTRIBUTED_RESOURCE_COMPETE_CODE, String.format("Resource was locked. Lock [%s]", distributedLock.name()));
        }
        return proceedingJoinPoint.proceed();
    }

    @AfterReturning("@annotation(com.summer.frame.redis.lock.annotation.DistributedLock)")
    public void afterReturning(JoinPoint joinPoint) throws Throwable {
        this.unlock(joinPoint);
    }

    @AfterThrowing(value = "@annotation(com.summer.frame.redis.lock.annotation.DistributedLock)", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Exception exception) throws Throwable {
        if (exception instanceof CustomizeException) {
            CustomizeException customizeException = (CustomizeException) exception;
            // 449异常码, 不释放锁
            if (customizeException.getCode() == DISTRIBUTED_RESOURCE_COMPETE_CODE) {
                return;
            }
        }
        this.unlock(joinPoint);
    }

    private void unlock(JoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        DistributedLock distributedLock = methodSignature.getMethod().getAnnotation(DistributedLock.class);
        this.unlock(distributedLock.name());
    }

    private void unlock(String name) throws Throwable {
        Boolean lockDeletable = stringRedisTemplate.delete(name);
        if (BooleanUtils.isFalse(lockDeletable)) {
            throw new Exception(String.format("Can not unlock. Lock [%s]", name));
        }
    }

    @Autowired
    public DistributedLockHandler(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

}