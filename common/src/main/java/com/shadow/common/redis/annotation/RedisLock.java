package com.shadow.common.redis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * redis锁
 * 用于接口幂等
 * 适用：弱网、并发请求(有极小概率bug，需升级2.1以上)
 * @author cuipeng 2021/1/30 23:54
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisLock {

    /**
     * 业务类型
     * 可为空，为空时必传serialNumber，否则锁失效
     * 存放目录为  redis_lock:业务类型:    例：  redis_lock:staticCode:  或多级目录  redis_lock:merchant:apply:
     */
    String businessType() default "";

    /**
     * 锁定的参数
     * 默认流水号serialNumber
     */
    String lockParam() default "serialNumber";

}
