package com.iluwatar.algorithm.backtracking.sudoku.solver;

public class SudokuSolver {
    // Kiểm tra xem có thể đặt số num vào vị trí [row, col] hay không
    private boolean isSafe(char[][] board, int row, int col, char num) {
        for (int d = 0; d < board.length; d++) {
            if (board[row][d] == num) {
                return false;
            }
            if (board[d][col] == num) {
                return false;
            }
            if (board[row - row % 3 + d / 3][col - col % 3 + d % 3] == num) {
                return false;
            }
        }
        return true;
    }

    // Giải bài toán Sudoku
    private boolean solveSudoku(char[][] board) {
        int n = board.length;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] == '.') {
                    for (char num = '1'; num <= '9'; num++) {
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveSudoku(board)) {
                                return true;
                            } else {
                                board[row][col] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    // Hàm chính để giải bài toán Sudoku
    public void solve(char[][] board) {
        if (solveSudoku(board)) {
            printBoard(board);
        } else {
            System.out.println("Không có lời giải");
        }
    }

    // In bàn cờ
    private void printBoard(char[][] board) {
        for (int r = 0; r < board.length; r++) {
            for (int d = 0; d < board[r].length; d++) {
                System.out.print(board[r][d]);
                System.out.print(" ");
            }
            System.out.print("\n");

            if ((r + 1) % 3 == 0)
                System.out.print("");
        }
    }

    public static void main(String[] args) {
        SudokuSolver solver = new SudokuSolver();
        char[][] board = new char[][] {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        solver.solve(board);
    }
}
