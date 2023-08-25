package backtracking.other.sudoku;

/**
 * leetcode 37 hard 解数独
 * 
 * 编写一个程序，通过填充空格来解决数独问题。
 * 数独的解法需 遵循如下规则：
 *  数字 1-9 在每一行只能出现一次。
 *  数字 1-9 在每一列只能出现一次。
 *  数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 */
public class SudokuSolver {
    public void solveSudoku(char[][] board) {
        backtrack(board);
    }

    // private void backtrack(char[][] board, int row) {
    //     if (row == board.length) {
    //         return;
    //     }

    //     for (int col = 0; col < 9; col++) {
    //         if (board[row][col] == '.') {
    //             for (int i = 1; i <= 9; i++) {
    //                 if (isValid(board, row, col, i)) {
    //                     board[row][col] = (char) i;
    //                     backtrack(board, row + 1);
    //                     board[row][col] = '.';
    //                 }
    //             }
    //         }
    //     }
    // }

    // 在棋盘的i，j位置放上一个数num是否合理
    private boolean isValid(char[][] board, int i, int j, char num) {
        // 同一行的位置上
        for (int col = 0; col < 9; col++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        // 同一列的位置上
        for (int row = 0; row < 9; row++) {
            if (board[row][j] == num) {
                return false;
            }
        }

        // 在3*3的区域内
        int X = (i / 3) * 3;
        int Y = (j / 3) * 3;

        for (int x = X; x <= X + 2; x++) {
            for (int y = Y; y <= Y + 2; y++) {
                if (board[x][y] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    // 之所以设置成boolean，是相当于剪枝
    public boolean backtrack(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char val = '1'; val <= '9'; val++) {
                        if (isValid(board, i, j, val)) {
                            board[i][j] = (char) val;
                            if (backtrack(board)) {
                                return true;
                            }
                            board[i][j] = '.';
                        }
                    }
                }
            }
        }

        return false;
    }
}
