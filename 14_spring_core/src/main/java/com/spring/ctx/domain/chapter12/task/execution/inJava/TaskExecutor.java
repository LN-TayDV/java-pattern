package com.spring.ctx.domain.chapter12.task.execution.inJava;

import java.util.concurrent.Executor;

@FunctionalInterface
public interface TaskExecutor extends Executor {

    @Override
    void execute(Runnable task);
}