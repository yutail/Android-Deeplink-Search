package yutailuo.androiddeeplinksearch.core;

import android.content.Intent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yutailuo on 10/7/16.
 */

public class DefaultConfigurationFactory {

    public static Map<String, Intent> createDeeplinkMap() {
        Map<String, Intent> deeplinkMap = new HashMap<>();
        return deeplinkMap;
    }

    public static Executor createExecutor(int threadPoolSize, int threadPriority) {
        return new ThreadPoolExecutor(threadPoolSize, threadPoolSize, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>(), createThreadFactory(threadPriority, "search-pool-"));
    }

    private static ThreadFactory createThreadFactory(int threadPriority, String threadNamePrefix) {
        return new DefaultThreadFactory(threadPriority, threadNamePrefix);
    }

    private static class DefaultThreadFactory implements ThreadFactory {

        private static final AtomicInteger mPoolNumber = new AtomicInteger(1);

        private final ThreadGroup mGroup;
        private final AtomicInteger mThreadNumber = new AtomicInteger(1);
        private final String mNamePrefix;
        private final int mThreadPriority;

        DefaultThreadFactory(int threadPriority, String threadNamePrefix) {
            this.mThreadPriority = threadPriority;
            mGroup = Thread.currentThread().getThreadGroup();
            mNamePrefix = threadNamePrefix + mPoolNumber.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(mGroup, r, mNamePrefix + mThreadNumber.getAndIncrement(), 0);
            if (t.isDaemon()) t.setDaemon(false);
            t.setPriority(mThreadPriority);
            return t;
        }
    }
}
