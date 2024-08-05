package com.spring.ctx.domain.chapter12.task.execution.inSpring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import java.util.UUID;

@Slf4j
public class RandomStringPrinter {

    private final TaskExecutor taskExecutor;

    public RandomStringPrinter(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public void executeTask() {
        for(int i=0; i < 10; ++ i) {
            final int index = i;
            taskExecutor.execute(() -> LOGGER.info("{}: {}", index , UUID.randomUUID().toString().substring(0, 8)));
        }
    }
}
