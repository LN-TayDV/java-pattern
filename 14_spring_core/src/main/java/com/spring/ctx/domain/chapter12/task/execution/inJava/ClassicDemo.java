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
