package com.shadow.common.redis.aspect;

import com.shadow.common.bean.ResultDTO;
import com.shadow.common.redis.annotation.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * redisLock AOP
 * @author cuipeng 2021/1/31 0:04
 */
@Aspect
@Component
@Slf4j
public class RedisLockAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Around("@annotation(com.shadow.common.redis.annotation.RedisLock)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 锁状态
        boolean locked = false;
        // 锁名
        String lockName = null;

        Object[] params = joinPoint.getArgs();

        if(params!=null) {
            MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
            Method method = methodSignature.getMethod();

            RedisLock redisLock = method.getAnnotation(RedisLock.class);

            // 自定义目录
            String businessType = redisLock.businessType();
            lockName = StringUtils.isEmpty(businessType) ? "redis_lock:" : "redis_lock:"+businessType+":";

            // 锁定参数
            for(Object o : params) {
                String lockParam = redisLock.lockParam();
                String paramValue = getFieldValue(lockParam, o, methodSignature);

                if(StringUtils.isEmpty(paramValue)) continue;

                // 加锁
                locked = stringRedisTemplate.opsForValue().setIfAbsent((lockName = lockName+paramValue), "lock", 1, TimeUnit.MINUTES);

                if(!locked) {
                    log.error("{}.{}请求重复,lockName={}", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(), lockName);
                    return ResultDTO.timeout();
                }
            }
        }

        Object o;
        try {
            o = joinPoint.proceed();
        } finally {
            // 解锁
            if(locked) {
                stringRedisTemplate.delete(lockName);
            }
        }

        return o;
    }



    /**
     * 获取对象字段value
     */
    private String getFieldValue(String fieldName, Object o, MethodSignature methodSignature) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // String请求参数
        if(o instanceof String) {
            String[] parameterNames = methodSignature.getParameterNames();
            if("serialNumber".equals(parameterNames[0])) return o.toString();
        }

        // json请求体
        String getter = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
        Method method = o.getClass().getMethod(getter, new Class[]{});

        Object value = method.invoke(o, new Object[]{});

        return value==null ? "" : value.toString();
    }

}
