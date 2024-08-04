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
package com.iluwatar.algorithm.theories.on.internet.backtracking.nqueens;

public class NQueens {
    // Kiểm tra xem có thể đặt quân hậu tại vị trí [row, col] hay không
    private boolean isSafe(char[][] board, int row, int col) {
        // Kiểm tra hàng ngang bên trái
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 'Q') {
                return false;
            }
        }

        // Kiểm tra đường chéo phía trên bên trái
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // Kiểm tra đường chéo phía dưới bên trái
        for (int i = row, j = col; i < board.length && j >= 0; i++, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    // Giải bài toán N-Queens
    private boolean solveNQueens(char[][] board, int col) {
        // Nếu đã đặt đủ quân hậu
        if (col >= board.length) {
            return true;
        }

        // Thử đặt quân hậu ở từng hàng
        for (int i = 0; i < board.length; i++) {
            if (isSafe(board, i, col)) {
                // Đặt quân hậu
                board[i][col] = 'Q';

                // Gọi đệ quy để đặt quân hậu tiếp theo
                if (solveNQueens(board, col + 1)) {
                    return true;
                }

                // Nếu không thành công, bỏ quân hậu
                board[i][col] = '.';
            }
        }

        // Nếu không thể đặt quân hậu ở cột này, trả về false
        return false;
    }

    // In bàn cờ
    private void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Hàm chính để giải bài toán N-Queens
    public void solve(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        if (solveNQueens(board, 0)) {
            printBoard(board);
        } else {
            System.out.println("Không có lời giải");
        }
    }

    public static void main(String[] args) {
        int n = 8; // Kích thước bàn cờ
        NQueens queens = new NQueens();
        queens.solve(n);
    }
}

