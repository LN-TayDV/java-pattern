package com.spring.ctx.domain.chapter12.task.execution.inJava;

import com.spring.ctx.domain.chapter12.task.execution.inJava.sort.BubbleSort;
import com.spring.ctx.domain.chapter12.task.execution.inJava.sort.HeapSort;
import com.spring.ctx.domain.chapter12.task.execution.inJava.sort.InsertionSort;
import com.spring.ctx.domain.chapter12.task.execution.inJava.sort.MergeSort;
import com.spring.ctx.domain.chapter12.task.execution.inJava.sort.QuickSort;
import com.spring.ctx.domain.chapter12.task.execution.inJava.sort.ShellSort;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ClassicDemo {

    public static void main(String... args) {
        int[] arr = new Random()
            .ints(100_000, 0, 500_000)
            .toArray(); // (1)

        var algsMonitor = new ThreadPoolMonitor(); // (2)

        var monitor = new Thread(algsMonitor);

        var executor = new ThreadPoolExecutor(
            2,
            4,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>()
        ); // (3)

        algsMonitor.setExecutor(executor);

        List.of(new BubbleSort(arr), // (4)
                new InsertionSort(arr),
                new HeapSort(arr),
                new MergeSort(arr),
                new QuickSort(arr),
                new ShellSort(arr)).forEach(sort -> executor.execute(sort));

        monitor.start(); // (5)
        executor.shutdown();

        try {
            executor.awaitTermination(30, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            monitor.join(); // Đảm bảo rằng thread giám sát hoàn thành
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
