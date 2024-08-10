/*
 * This project is licensed under the MIT license. Module model-view-viewmodel is using ZK framework licensed under LGPL (see lgpl-3.0.txt).
 *
 * The MIT License
 * Copyright © 2014-2022 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
