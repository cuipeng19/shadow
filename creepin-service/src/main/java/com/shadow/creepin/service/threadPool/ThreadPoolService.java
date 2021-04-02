package com.shadow.creepin.service.threadPool;

import com.google.common.util.concurrent.ListeningExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 对核心接口扩充
 * @author cuipeng 2020/4/21 11:16
 */
public interface ThreadPoolService extends ThreadPool {

    /**
     * 获取线程池
     * <p>适用场景： 任务时间短</p>
     */
    ExecutorService getFastThreadPool();

    /**
     * 获取线程池
     * <p>适用场景： 任务时间短</p>
     * <p>对fastThreadPool监听，注册回调方法</p>
     */
    ListeningExecutorService getFastListeningThreadPool();

    /**
     * 获取线程池
     * <p>适用场景： 任务时间较长，不需立即返回</p>
     */
    ExecutorService getCacheThreadPool();

    /**
     * 获取线程池
     * <p>适用场景： 任务时间较长，不需立即返回</p>
     * <p>对cacheThreadPool监听，注册回调方法</p>
     */
    ListeningExecutorService getCacheListeningThreadPool();

    /**
     * 获取线程池
     * <p>适用场景： 延时执行</p>
     */
    ScheduledExecutorService getScheduledThreadPool();

}
