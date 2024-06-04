package com.iluwatar.algorithm.dynamic.programming;

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

