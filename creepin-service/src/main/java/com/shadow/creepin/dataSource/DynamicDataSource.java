package com.shadow.creepin.dataSource;

import com.shadow.creepin.config.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.reflect.Field;

/**
 * 动态数据源
 * 读写分离：aop根据service层操作方法，修改Mybatis数据源
 *
 * @author cuipeng 2021/6/23 14:46
 */
@Aspect
@Component
@Slf4j
@Order(0)
public class DynamicDataSource {


    /**
     * 从库
     */
    @Before("execution(* com.shadow.creepin.service.*.select*(..)) || execution(* com.shadow.creepin.service.*.get*(..))")
    public void setReadDataSourceType() throws Exception {
        DataSource dataSource = (DataSource) SpringContextUtil.getBean("slaveDataSource");
        // 修改mybatis的数据源
        SqlSessionFactory sqlSessionFactoryBean = (SqlSessionFactory)SpringContextUtil.getBean(SqlSessionFactory.class);
        Environment environment = sqlSessionFactoryBean.getConfiguration().getEnvironment();
        Field dataSourceField = environment.getClass().getDeclaredField("dataSource");
        dataSourceField.setAccessible(true);//跳过检查
        dataSourceField.set(environment,dataSource);//修改mybatis的数据源
        log.info("数据源切换到：slave");
    }


    /**
     * 主库
     */
    @Before("execution(* com.shadow.creepin.service.*.insert*(..)) || execution(* com.shadow.creepin.service.*.update*(..)) || execution(* com.shadow.creepin.service.*.delete*(..)) || execution(* com.shadow.creepin.service.*.add*(..)) || execution(* com.shadow.creepin.service.*.save*(..))")
    public void serWriteDataSourceType() throws Exception {
        DataSource dataSource = (DataSource) SpringContextUtil.getBean("masterDataSource");
        // 修改mybatis的数据源
        SqlSessionFactory sqlSessionFactoryBean = (SqlSessionFactory)SpringContextUtil.getBean(SqlSessionFactory.class);
        Environment environment = sqlSessionFactoryBean.getConfiguration().getEnvironment();
        Field dataSourceField = environment.getClass().getDeclaredField("dataSource");
        dataSourceField.setAccessible(true);//跳过检查
        dataSourceField.set(environment,dataSource);//修改mybatis的数据源
        log.info("数据源切换到：master");
    }


}
