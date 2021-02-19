package com.shadow.creepin.service.threadPool;

import java.util.concurrent.ExecutorService;

/**
 * 线程池核心接口
 * @author cuipeng 2020/4/21 11:08
 */
public interface ThreadPool {

    /**
     * <p>
     * 推荐使用方式：ThreadPoolService.get***ThreadPool()
     * </p>
     */
    ExecutorService getThreadPool(int poolType);

}
