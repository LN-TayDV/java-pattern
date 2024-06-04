package com.iluwatar.algorithm.backtracking.nqueens;

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

