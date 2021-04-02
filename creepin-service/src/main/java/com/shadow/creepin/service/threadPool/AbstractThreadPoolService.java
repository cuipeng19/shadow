package com.shadow.creepin.service.threadPool;

import com.google.common.util.concurrent.ListeningExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author cuipeng 2020/4/21 11:20
 */
public abstract class AbstractThreadPoolService implements ThreadPoolService {


    @Override
    public ExecutorService getFastThreadPool() {
        return getThreadPool(10);
    }

    @Override
    public ListeningExecutorService getFastListeningThreadPool() {
        return (ListeningExecutorService) getThreadPool(11);
    }

    @Override
    public ExecutorService getCacheThreadPool() {
        return getThreadPool(20);
    }

    @Override
    public ListeningExecutorService getCacheListeningThreadPool() {
        return (ListeningExecutorService) getThreadPool(21);
    }

    @Override
    public ScheduledExecutorService getScheduledThreadPool() {
        return (ScheduledExecutorService)getThreadPool(30);
    }
}
