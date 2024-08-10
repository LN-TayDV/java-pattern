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
package com.iluwatar.async.method.invocation.lamda;

import com.iluwatar.async.method.invocation.lamda.async.callback.AsyncCallback;
import com.iluwatar.async.method.invocation.lamda.async.callback.Callback;
import com.iluwatar.async.method.invocation.lamda.async.executor.ThreadExecutor;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

@Slf4j
public class App {

    private static final String ROCKET_LAUNCH_LOG_PATTERN = "Space rocket <%s> launched successfully";

    public static void main(String[] args) throws Exception {

        // construct a new executor that will run async tasks
        var executor = new ThreadExecutor();

        final var asyncResult1 = executor.startProcess("Deploying lunar rover", lazyval("Test", 200), (name , task) -> {
            return AsyncCallback
                .create()
                .onComplete((value) ->  log(name + " <" + value + ">"))
                .onError((ex) ->  log(name + " failed: " + ex.getMessage()));
        });

        // emulate processing in the current thread while async tasks are running in their own threads
        Thread.sleep(350); // Oh boy, we are working hard here
        log("Mission command is sipping coffee");

        // wait for completion of the tasks
        final var result1 = executor.endProcess(asyncResult1);
        asyncResult1.await();


        // log the results of the tasks, callbacks log immediately when complete
        //log(String.format(ROCKET_LAUNCH_LOG_PATTERN, result1));

    }

    private static  Callable<Object> lazyval(Object value, long delayMillis) {
        return () -> {
            Thread.sleep(delayMillis);
            log(String.format(ROCKET_LAUNCH_LOG_PATTERN, value));
            return value;
        };
    }

    private static void log(String msg) {
        LOGGER.info(msg);
    }
}
