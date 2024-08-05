package com.spring.ctx.domain.chapter12.asynchronous.task.execution.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Slf4j
public class AsyncDemo {

    public static void main(String... args) throws IOException, ExecutionException, InterruptedException {
        try (var ctx = new AnnotationConfigApplicationContext(AsyncConfig.class)) {

            var asyncService = ctx.getBean("asyncService", AsyncService.class);

            for (int i = 0; i < 5; i++) {
                asyncService.asyncTask();
            }

            var result1 = asyncService.asyncWithReturn("John Mayer");
            var result2 = asyncService.asyncWithReturn("Eric Clapton");
            var result3 = asyncService.asyncWithReturn("BB King");

            Thread.sleep(6000);
            LOGGER.info(" >> Result1: " + result1.get());
            LOGGER.info(" >> Result2: " + result2.get());
            LOGGER.info(" >> Result3: " + result3.get());
            System.in.read();
        }
    }
}
