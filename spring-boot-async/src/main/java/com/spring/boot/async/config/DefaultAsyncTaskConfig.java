package com.spring.boot.async.config;

import com.spring.boot.async.utils.VisibleThreadPoolTaskExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@EnableAsync(proxyTargetClass = true)
@Configuration
public class DefaultAsyncTaskConfig {

    @Value("${async.task.corePoolSize: 10}")
    private int corePoolSize;

    @Value("${async.task.maxPoolSize: 200}")
    private int maxPoolSize;

    @Value("${async.task.queueCapacity: 10}")
    private int queueCapacity;

    @Value("${async.task.threadNamePrefix: artanis}")
    private String threadNamePrefix;

    // 自定义异步执行线程池
    @Bean(name = "asyncTaskExecutor")
    public TaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor executor = new VisibleThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
