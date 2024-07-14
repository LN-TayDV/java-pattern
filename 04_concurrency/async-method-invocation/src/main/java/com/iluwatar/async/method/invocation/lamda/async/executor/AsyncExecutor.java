package com.iluwatar.async.method.invocation.lamda.async.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;

import com.iluwatar.async.method.invocation.lamda.async.callback.Callback;
import com.iluwatar.async.method.invocation.lamda.async.result.Result;

public interface AsyncExecutor {

    Result startProcess(String name, Callable<Object> task, BiFunction<String, Callable<Object>, Callback> function);

    Object endProcess(Result asyncResult) throws ExecutionException, InterruptedException;
}
