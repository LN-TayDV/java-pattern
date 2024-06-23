package com.iluwatar.async.method.invocation.lamda.async.executor;

import com.iluwatar.async.method.invocation.lamda.async.result.AsyncResult;
import com.iluwatar.async.method.invocation.lamda.async.result.Result;
import com.iluwatar.async.method.invocation.lamda.async.callback.Callback;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;

public class ThreadExecutor implements AsyncExecutor {

    private final AtomicInteger idx = new AtomicInteger(0);

    @Override
    public Result startProcess(String name, Callable<Object> task, BiFunction<String, Callable<Object>, Callback> function) {
        var result = new AsyncResult(function.apply(name, task));

        new Thread(() -> {
            try {
                result.setValue(task.call());
            } catch (Exception ex) {
                result.setException(ex);
            }
        }, "executor-" + idx.incrementAndGet()).start();
        return result;
    }

    @Override
    public Object endProcess(Result asyncResult) throws ExecutionException, InterruptedException {
        if (!asyncResult.isCompleted()) {
            asyncResult.await();
        }
        return asyncResult.getValue();
    }
}