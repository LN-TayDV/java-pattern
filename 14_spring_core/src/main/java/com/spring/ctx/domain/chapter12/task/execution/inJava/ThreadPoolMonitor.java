package com.spring.ctx.domain.chapter12.task.execution.inJava;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
public class ThreadPoolMonitor implements Runnable {

    protected ThreadPoolExecutor executor;
    protected int printInterval = 200;

    @Override
    public void run() {
        try {
            while (executor.getActiveCount() > 0) {
                monitorThreadPool();
                Thread.sleep(printInterval);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void monitorThreadPool() {
        String strBuff = "CurrentPoolSize : " + executor.getPoolSize() +
            " - CorePoolSize : " + executor.getCorePoolSize() +
            " - MaximumPoolSize : " + executor.getMaximumPoolSize() +
            " - ActiveTaskCount : " + executor.getActiveCount() +
            " - CompletedTaskCount : " + executor.getCompletedTaskCount() +
            " - TotalTaskCount : " + executor.getTaskCount() +
            " - isTerminated : " + executor.isTerminated();
        LOGGER.debug(strBuff);
    }

    public void setExecutor(ThreadPoolExecutor executor) {
        this.executor = executor;
    }
}
