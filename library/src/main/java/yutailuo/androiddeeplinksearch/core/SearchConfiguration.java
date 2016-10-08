package yutailuo.androiddeeplinksearch.core;

import android.content.Intent;

import java.util.Map;
import java.util.concurrent.Executor;

public class SearchConfiguration {

    public static final String TAG = SearchConfiguration.class.getSimpleName();

    private Map<String, Intent> mDeeplinkMap;

    private Executor mTaskExecutor;
    private int mThreadPoolSize;
    private int mThreadPriority;

    private SearchConfiguration(final Builder builder) {
        mDeeplinkMap = builder.mDeeplinkMap;
        mTaskExecutor = builder.mTaskExecutor;
        mThreadPoolSize = builder.mThreadPoolSize;
        mThreadPriority = builder.mThreadPriority;
    }

    public Map<String, Intent> getDeeplinkMap() {
        return mDeeplinkMap;
    }

    public Executor getTaskExecutor() {
        return mTaskExecutor;
    }

    public static class Builder {

        public static final int DEFAULT_THREAD_POOL_SIZE = 3;
        public static final int DEFAULT_THREAD_PRIORITY = Thread.NORM_PRIORITY - 2;

        private Map<String, Intent> mDeeplinkMap = null;

        private Executor mTaskExecutor = null;
        private int mThreadPoolSize = DEFAULT_THREAD_POOL_SIZE;
        private int mThreadPriority = DEFAULT_THREAD_PRIORITY;

        public Builder() {
        }

        public Builder setDeeplinkMap(Map<String, Intent> deeplinkMap) {
            mDeeplinkMap = deeplinkMap;
            return this;
        }

        public Builder setTaskExecutor(Executor taskExecutor) {
            mTaskExecutor = taskExecutor;
            return this;
        }

        public Builder setThreadPoolSize(int threadPoolSize) {
            mThreadPoolSize = threadPoolSize;
            return this;
        }

        public Builder setThreadPriority(int threadPriority) {
            mThreadPriority = threadPriority;
            return this;
        }

        public SearchConfiguration build() {
            initEmptyFieldsWithDefaultValues();
            return new SearchConfiguration(this);
        }

        private void initEmptyFieldsWithDefaultValues() {
            if (mDeeplinkMap == null) {
                mDeeplinkMap = DefaultConfigurationFactory.createDeeplinkMap();
            }
            if (mTaskExecutor == null) {
                mTaskExecutor = DefaultConfigurationFactory.createExecutor(mThreadPoolSize, mThreadPriority);
            }
        }
    }
}
