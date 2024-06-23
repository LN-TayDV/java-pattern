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
package com.iluwatar.algorithm.theories.search;

public class BinarySearch {

    /**
     * Hàm tìm kiếm nhị phân trong mảng đã được sắp xếp.
     * @param array mảng đầu vào đã sắp xếp
     * @param key giá trị cần tìm
     * @return chỉ số của phần tử cần tìm hoặc -1 nếu không tìm thấy
     */
    public static int binarySearch(int[] array, int key) {
        int left = 0;
        int right = array.length - 1;

        // Vòng lặp tìm kiếm
        while (left <= right) {
            // Tính chỉ số giữa mảng
            int mid = left + (right - left) / 2;

            // Nếu phần tử giữa bằng key, trả về chỉ số giữa
            if (array[mid] == key) {
                return mid;
            }

            // Nếu phần tử giữa nhỏ hơn key, bỏ qua nửa trái
            if (array[mid] < key) {
                left = mid + 1;
            }
            // Nếu phần tử giữa lớn hơn key, bỏ qua nửa phải
            else {
                right = mid - 1;
            }
        }
        // Nếu không tìm thấy key, trả về -1
        return -1;
    }

    /**
     * Hàm main để kiểm tra Binary Search
     * @param args không sử dụng
     */
    public static void main(String[] args) {
        int[] array = {1, 1, 2, 3, 3, 4, 5, 5, 6, 9};
        int key = 5;

        // Gọi hàm tìm kiếm nhị phân
        int result = binarySearch(array, key);

        // In kết quả tìm kiếm
        if (result != -1) {
            System.out.println("Phần tử " + key + " được tìm thấy tại chỉ số: " + result);
        } else {
            System.out.println("Phần tử " + key + " không được tìm thấy trong mảng.");
        }
    }
}
