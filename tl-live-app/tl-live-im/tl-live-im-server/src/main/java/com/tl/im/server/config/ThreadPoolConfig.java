package com.tl.im.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig {

    @Value("${tllive.im.thread.pool.corePoolSize:2}")
    private int corePoolSize;

    @Value("${tllive.im.thread.pool.maxPoolSize:10}")
    private int maxPoolSize;

    @Value("${tllive.im.thread.pool.queueCapacity:10000}")
    private int queueCapacity;

    @Value("${tllive.im.thread.pool.keepAliveTime:600}")
    private int keepAliveTime;

    @Bean("asyncExecutor")
    public Executor threadPoolExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setMaxPoolSize(maxPoolSize);
        taskExecutor.setKeepAliveSeconds(keepAliveTime);
        taskExecutor.setQueueCapacity(queueCapacity);
        taskExecutor.setThreadNamePrefix("CustomThreadPoolExecutor");
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        return  taskExecutor;
    }

}
