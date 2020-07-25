package com.shadow.stan.gateway.aspect;

import com.alibaba.fastjson.JSONObject;
import com.shadow.common.bean.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * aop日志
 * @author cuipeng 2020/7/9 11:17
 */
//@Aspect
//@Component
@Slf4j
public class LogAspect {

    @Around("execution(* com.shadow.stan.gateway.api.*Controller.*(..))")
    public Object controllerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();

        // 请求头
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        String[] paramNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        Object[] paramValues = joinPoint.getArgs();
        JSONObject requestJson = new JSONObject();
        for(int i=0; i<paramNames.length; i++) {
            requestJson.put(paramNames[i], paramValues[i]);
        }
        log.info("Request:{},[{}::{}],param:{}", className, methodName, requestJson.toJSONString());

        // 方法调用
        Object o = joinPoint.proceed();

        // 响应
        long duration = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli() - startTime;
        ResultDTO dto = (ResultDTO)o;
        log.info("Response[{}]:{}", duration, dto.toString());
        return o;
    }
}
