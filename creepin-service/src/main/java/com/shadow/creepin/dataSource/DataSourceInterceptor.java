package com.shadow.creepin.dataSource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 数据源拦截器
 * @author cuipeng 2021/6/23 14:46
 */
@Aspect
@Component
@Slf4j
@Order(Ordered.LOWEST_PRECEDENCE-1)
public class DataSourceInterceptor {


    @Before("execution(* com.shadow.creepin.dao.*.select*(..)) || execution(* com.shadow.creepin.dao.*.get*(..))")
    public void setReadDataSourceType() {
        DynamicDataSource.slave();
        log.info("数据源切换到：slave");
    }


    @Before("execution(* com.shadow.creepin.dao.*.insert*(..)) || execution(* com.shadow.creepin.dao.*.update*(..)) || execution(* com.shadow.creepin.dao.*.delete*(..)) || execution(* com.shadow.creepin.dao.*.add*(..)) || execution(* com.shadow.creepin.dao.*.save*(..))")
    public void serWriteDataSourceType() {
        DynamicDataSource.master();
        log.info("数据源切换到：master");
    }


    @AfterReturning("execution(* com.shadow.creepin.dao.*.*(..))")
    public void clearDataSource() {
        DynamicDataSource.clear();
        log.info("数据源清空");
    }

}
