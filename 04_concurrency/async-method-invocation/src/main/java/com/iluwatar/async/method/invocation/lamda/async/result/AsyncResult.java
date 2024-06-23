package com.iluwatar.async.method.invocation.lamda.async.result;

import com.iluwatar.async.method.invocation.lamda.async.callback.Callback;
import java.util.concurrent.ExecutionException;

public class AsyncResult implements Result {

    static final int RUNNING = 1;
    static final int FAILED = 2;
    static final int COMPLETED = 3;

    final Object lock = new Object();
    final Callback callback;

    volatile int state = RUNNING;
    Object value;
    Exception exception;

    public AsyncResult(Callback callback) {
        this.callback = callback;
    }

    boolean hasCallback() {
        return callback != null;
    }

    public void setException(Exception exception) {
        this.exception = exception;
        this.state = FAILED;
        if (hasCallback()) {
            callback.error(exception);
        }
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    @Override
    public boolean isCompleted() {
        return state > RUNNING;
    }

    @Override
    public Object getValue() throws ExecutionException {
        if (state == COMPLETED) {
            return value;
        } else if (state == FAILED) {
            throw new ExecutionException(exception);
        } else {
            throw new IllegalStateException("Execution not completed yet");
        }
    }

    public void setValue(Object value) {
        this.value = value;
        this.state = COMPLETED;
        if (hasCallback()) {
            callback.complete(value);
        }
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    @Override
    public void await() throws InterruptedException {
        synchronized (lock) {
            while (!isCompleted()) {
                lock.wait();
            }
        }
    }
}
