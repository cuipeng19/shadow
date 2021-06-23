package com.shadow.creepin.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.shadow.creepin.dataSource.DynamicDataSource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据库配置
 * 读写分离：
 * aop根据数据库操作方法，通过ThreadLocal记录数据源类型
 * 实现AbstractRoutingDataSource，获取数据源类型
 *
 * @author cuipeng 2021/6/23 10:32
 */
@Configuration
@Data
public class DatabaseConfig {


    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.slave")
    public DataSource slaveDataSource() {
        return DruidDataSourceBuilder.create().build();
    }


    /**
     * 设置动态数据源
     */
    @Bean
    public DynamicDataSource dynamicDataSource(DataSource masterDataSource, @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DynamicDataSource.DatabaseType.Master, masterDataSource);
        targetDataSource.put(DynamicDataSource.DatabaseType.Slave, slaveDataSource);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSource);
        dataSource.setDefaultTargetDataSource(masterDataSource);
        dataSource.afterPropertiesSet();
        return dataSource;
    }
}
