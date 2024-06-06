package com.iluwatar.async.method.invocation.lamda.async.executor;

import com.iluwatar.async.method.invocation.generic.AsyncResult;
import com.iluwatar.async.method.invocation.lamda.async.result.Result;
import com.iluwatar.async.method.invocation.lamda.async.callback.Callback;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;

public interface AsyncExecutor {

    Result startProcess(String  name, Callable<Object> task, BiFunction<String, Callable<Object>, Callback> function);

    Object endProcess(Result asyncResult) throws ExecutionException, InterruptedException;
}
