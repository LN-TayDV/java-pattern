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
package com.iluwatar.algorithm.theories.on.self.learning.Sorting;

public class Bubble {

    // Phương thức sắp xếp nổi bọt với mảng các đối tượng Comparable
    public static void sort(Comparable[] arr) {
        int n = arr.length; // Lấy số lượng phần tử trong mảng

        // Lặp qua tất cả các phần tử trong mảng
        for (int i = 0; i < n - 1; i++) {
            // Biến để kiểm tra xem có thực hiện hoán đổi nào không
            boolean swapped = false;

            // Lặp qua mảng, bỏ qua các phần tử đã sắp xếp
            for (int j = 0; j < n - i - 1; j++) {
                // So sánh phần tử hiện tại với phần tử kế tiếp
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    // Hoán đổi nếu phần tử hiện tại lớn hơn phần tử kế tiếp
                    Comparable temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    // Đặt swapped thành true vì đã thực hiện hoán đổi
                    swapped = true;
                }
            }

            // Nếu không có hoán đổi nào trong vòng lặp bên trong, mảng đã được sắp xếp
            if (!swapped) {
                break;
            }
        }
    }

    // Phương thức main để kiểm tra thuật toán sắp xếp
    public static void main(String[] args) {
        // Mảng các đối tượng Integer cần sắp xếp
        Integer[] arr = {64, 34, 25, 12, 22, 11, 90};

        System.out.println("Mảng trước khi sắp xếp:");
        printArray(arr);

        sort(arr); // Gọi phương thức sắp xếp nổi bọt

        System.out.println("Mảng sau khi sắp xếp:");
        printArray(arr);
    }

    // Phương thức in mảng
    public static void printArray(Comparable[] arr) {
        for (Comparable num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
