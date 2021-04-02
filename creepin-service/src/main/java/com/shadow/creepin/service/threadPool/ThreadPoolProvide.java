package com.shadow.creepin.service.threadPool;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * @author cuipeng 2020/4/21 11:34
 */
@Service
public class ThreadPoolProvide extends AbstractThreadPoolService {

    /**
     * type: 10
     * 适用场景： 任务时间短
     */
    private ExecutorService fastThreadPool;

    /**
     * type: 11
     * 对10监听，注册回调方法
     */
    private ListeningExecutorService fastListeningThreadPool;

    /**
     * type: 20
     * 适用场景： 任务时间较长，不需立即返回
     */
    private ExecutorService cacheThreadPool;

    /**
     * type: 21
     * 对20监听，注册回调方法
     */
    private ListeningExecutorService cacheListeningThreadPool;

    /**
     * type: 30
     * 适用场景： 延时执行
     */
    private ScheduledExecutorService scheduleThreadPool;


    /**
     * 私有构造
     */
    private ThreadPoolProvide() {
        this.fastThreadPool = new ThreadPoolExecutor(0,
                                                    16,
                                                    60L,
                                                    TimeUnit.SECONDS,
                                                    new SynchronousQueue<>(),
                                                    Executors.defaultThreadFactory(),
                                                    new CallerRunsPolicy());;
        this.fastListeningThreadPool = MoreExecutors.listeningDecorator(this.fastThreadPool);
        this.cacheThreadPool = new ThreadPoolExecutor(0,
                                                        4,
                                                        60L,
                                                        TimeUnit.SECONDS,
                                                        new ArrayBlockingQueue<>(300),
                                                        Executors.defaultThreadFactory(),
                                                        new CallerRunsPolicy());
        this.cacheListeningThreadPool = MoreExecutors.listeningDecorator(this.cacheThreadPool);
        this.scheduleThreadPool = Executors.newScheduledThreadPool(1);
    }


    @Override
    public ExecutorService getThreadPool(int poolType) {
        switch (poolType) {
            case 10:
                return this.fastThreadPool;
            case 11:
                return this.fastListeningThreadPool;
            case 20:
                return this.cacheThreadPool;
            case 21:
                return this.cacheListeningThreadPool;
            case 30:
                return this.scheduleThreadPool;
            default:
                return this.fastThreadPool;
        }
    }


    /**
     * 拒绝策略
     * 交给调用主线程执行
     */
    private class CallerRunsPolicy implements RejectedExecutionHandler {

        public CallerRunsPolicy() {
        }

        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            if (!e.isShutdown()) {
                r.run();
            }
        }
    }

}
