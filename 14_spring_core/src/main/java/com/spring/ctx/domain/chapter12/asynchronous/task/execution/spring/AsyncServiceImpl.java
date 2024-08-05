package com.spring.ctx.domain.chapter12.asynchronous.task.execution.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Slf4j
public class AsyncServiceImpl implements AsyncService{

    @Async
    @Override
    public void asyncTask() {
        LOGGER.info("Start execution of async. task");
        try {
            Thread.sleep(10000);
        } catch (Exception ex) {
            LOGGER.error("Task Interruption", ex);
        }
        LOGGER.info("Complete execution of async. task");
    }

    @Async
    @Override
    public Future<String> asyncWithReturn(String name) {
        LOGGER.info("Start execution of async. task with return for {}",name);

        try {
            Thread.sleep(5000);
        } catch (Exception ex) {
            LOGGER.error("Task Interruption", ex);
        }

        LOGGER.info("Complete execution of async. task with return for {}", name);

        return CompletableFuture.completedFuture("Hello: " + name);
    }
}
