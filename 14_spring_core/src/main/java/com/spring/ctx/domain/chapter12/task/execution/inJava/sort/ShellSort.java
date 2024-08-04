package com.spring.ctx.domain.chapter12.task.execution.inJava.sort;

public class ShellSort extends AbstractSort {

    public ShellSort(int[] array) {
        super(array);
    }

    @Override
    public void sort(int[] array) {
        int n = array.length;

        // Bắt đầu với khoảng cách lớn, sau đó giảm dần khoảng cách
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Thực hiện sắp xếp bằng cách chèn với khoảng cách này
            for (int i = gap; i < n; i++) {
                int temp = array[i];
                int j;
                // Dịch chuyển các phần tử đã được sắp xếp trước đó với khoảng cách gap
                // để tìm vị trí thích hợp cho array[i]
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                }
                // Đặt temp vào vị trí thích hợp
                array[j] = temp;
            }
        }
    }
}

