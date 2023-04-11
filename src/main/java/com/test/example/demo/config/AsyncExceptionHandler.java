package com.test.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
@Component
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(final Throwable ex, final Method method, final Object... args) {
        log.error("Method : %s Arguments: [%s] Error message: %s, trace: %s".formatted(
                method.getName(),
                Arrays.toString(args),
                ex.getMessage(),
                ex.getStackTrace()
            )
        );
    }
}
