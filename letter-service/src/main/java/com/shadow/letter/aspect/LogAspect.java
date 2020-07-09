package com.shadow.letter.aspect;

import com.alibaba.fastjson.JSONObject;
import com.shadow.common.bean.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author cuipeng 2020/7/9 11:17
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Around("execution(* com.shadow.letter.api.*Controller.*(..))")
    public Object controllerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String method = request.getMethod();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        String[] paramNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        Object[] paramValues = joinPoint.getArgs();
        JSONObject requestJson = new JSONObject();
        for(int i=0; i<paramNames.length; i++) {
            requestJson.put(paramNames[i], paramValues[i]);
        }
        log.info("Request:{},[{}::{}],param:{}", method, className, methodName, requestJson.toJSONString());

        Object o = joinPoint.proceed();

        long duration = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli() - startTime;
        ResultDTO dto = (ResultDTO)o;
        log.info("Response[{}]:{}", duration, dto.toString());
        return o;
    }
}
