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
package com.iluwatar.algorithm.theories.search.astar.programming.knapsack;

public class Knapsack {
    // Phương thức để tính giá trị tối đa mà knapsack có thể chứa
    public static int knapsack(int W, int[] weights, int[] values, int n) {
        // Tạo một mảng 2D để lưu trữ giá trị tối đa có thể đạt được
        int[][] dp = new int[n + 1][W + 1];

        // Tính toán giá trị tối đa có thể đạt được cho mỗi trọng lượng và số lượng mục
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    dp[i][w] = 0;
                else if (weights[i - 1] <= w)
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                else
                    dp[i][w] = dp[i - 1][w];
            }
        }

        // Trả về giá trị tối đa có thể đạt được
        return dp[n][W];
    }

    public static void main(String[] args) {
        int[] values = {60, 100, 120}; // Giá trị của các mục
        int[] weights = {10, 20, 30}; // Trọng lượng của các mục
        int W = 50; // Trọng lượng tối đa của knapsack
        int n = values.length; // Số lượng mục

        System.out.println("Giá trị tối đa mà knapsack có thể chứa là: " + knapsack(W, weights, values, n));
    }
}

