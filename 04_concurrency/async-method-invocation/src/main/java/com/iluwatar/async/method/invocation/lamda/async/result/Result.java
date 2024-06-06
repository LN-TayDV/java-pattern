package com.iluwatar.async.method.invocation.lamda.async.result;

import java.util.concurrent.ExecutionException;

public interface Result {

    boolean isCompleted();

    Object getValue() throws ExecutionException;

    void await() throws InterruptedException;
}
