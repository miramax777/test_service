package com.test.example.demo.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
@Configuration
public class AsyncConfig extends AsyncConfigurerSupport {

    private final AsyncExceptionHandler asyncExceptionHandler;

    public AsyncConfig(final AsyncExceptionHandler asyncExceptionHandler) {
        super();
        this.asyncExceptionHandler = asyncExceptionHandler;
    }

    @Override
    public Executor getAsyncExecutor() {
        final var executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(100);
        executor.setMaxPoolSize(500);
        executor.setKeepAliveSeconds(10);
        executor.setQueueCapacity(5);
        executor.setThreadNamePrefix("async-thread-");
        executor.initialize();

        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return asyncExceptionHandler;
    }
}
