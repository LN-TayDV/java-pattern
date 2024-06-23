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
