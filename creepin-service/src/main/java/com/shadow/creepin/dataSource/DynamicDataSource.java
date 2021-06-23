package com.shadow.creepin.dataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 * @author cuipeng 2021/6/23 14:22
 */
public class DynamicDataSource extends AbstractRoutingDataSource {


    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();



    @Override
    protected Object determineCurrentLookupKey() {
        return contextHolder.get();
    }



    public static void master() {
        contextHolder.set(DatabaseType.Master);
    }

    public static void slave() {
        contextHolder.set(DatabaseType.Slave);
    }

    public static void clear() {
        contextHolder.remove();
    }

    public static DatabaseType getType() {
        return contextHolder.get();
    }


    /**
     * 主从枚举
     */
    public enum DatabaseType {
        Master, Slave
    }

}
