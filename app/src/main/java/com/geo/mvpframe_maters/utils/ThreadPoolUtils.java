package com.geo.mvpframe_maters.utils;

import java.util.concurrent.*;

/**
 * 线程池，用于管理线程
 *
 */
public class ThreadPoolUtils {
    /**
     * 线程池对象
     */
    private static final ExecutorService mExecutorService;

    static {
        ThreadFactory namedThreadFactory = Thread::new;
        mExecutorService = new ThreadPoolExecutor(3,
                32,
                60,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(64),
                namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());

//        mExecutorService.shutdown();
//        while (true) {
//            if (mExecutorService.isTerminated()) {
//                System.out.println("结束了！");
//                break;
//            }
//            //Thread.sleep(200);
//        }
//        System.out.println("我还没结束");
    }

    public static void execute(Runnable runnable) {
        mExecutorService.execute(runnable);
    }


}
