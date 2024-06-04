package com.iluwatar.algorithm.Graph.search.astar.programming.fibonacci;

public class Fibonacci {
    // Phương thức để tính số Fibonacci thứ n
    public static int fibonacci(int n) {
        // Tạo một mảng để lưu trữ các giá trị Fibonacci đã tính
        int[] fib = new int[n + 1];
        fib[0] = 0;
        fib[1] = 1;

        // Tính toán và lưu trữ các số Fibonacci từ 2 đến n
        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }

        // Trả về số Fibonacci thứ n
        return fib[n];
    }

    public static void main(String[] args) {
        int n = 10; // Số Fibonacci cần tìm
        System.out.println("Số Fibonacci thứ " + n + " là: " + fibonacci(n));
    }
}

