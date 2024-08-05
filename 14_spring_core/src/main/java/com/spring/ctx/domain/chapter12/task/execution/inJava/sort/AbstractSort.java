package com.spring.ctx.domain.chapter12.task.execution.inJava.sort;

public abstract class AbstractSort implements IntSortingTask {

    private final int[] array;

    public AbstractSort(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        this.sort(array);
    }
}
