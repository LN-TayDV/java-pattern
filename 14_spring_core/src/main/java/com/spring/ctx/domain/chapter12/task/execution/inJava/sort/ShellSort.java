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

