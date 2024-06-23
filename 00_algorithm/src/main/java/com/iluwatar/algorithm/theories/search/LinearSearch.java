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

public class LinearSearch {

    /**
     * Hàm tìm kiếm tuyến tính trong mảng.
     * @param array mảng đầu vào
     * @param key giá trị cần tìm
     * @return chỉ số của phần tử cần tìm hoặc -1 nếu không tìm thấy
     */
    public static int linearSearch(int[] array, int key) {
        // Duyệt qua từng phần tử của mảng
        for (int i = 0; i < array.length; i++) {
            // Nếu phần tử tại vị trí i bằng với key
            if (array[i] == key) {
                // Trả về chỉ số i
                return i;
            }
        }
        // Nếu không tìm thấy key, trả về -1
        return -1;
    }

    /**
     * Hàm main để kiểm tra Linear Search
     * @param args không sử dụng
     */
    public static void main(String[] args) {
        int[] array = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        int key = 5;

        // Gọi hàm tìm kiếm tuyến tính
        int result = linearSearch(array, key);

        // In kết quả tìm kiếm
        if (result != -1) {
            System.out.println("Phần tử " + key + " được tìm thấy tại chỉ số: " + result);
        } else {
            System.out.println("Phần tử " + key + " không được tìm thấy trong mảng.");
        }
    }
}
