package backtracking.other.n_queens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode 51 hard N皇后问题
 * 
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 */

public class NQueens {
    List<List<String>> ans = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        // 初始化棋盘
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        backtrack(board, 0);

        return ans;
    }

    public void backtrack(char[][] board, int row) {
        if (row == board.length) {
            List<String> tmp = new ArrayList<>();
            // 把board拓下来
            for (int i = 0; i < board.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < board.length; j++) {
                    sb.append(board[i][j]);
                }
                tmp.add(sb.toString());
                sb = new StringBuilder();
            }
            ans.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = 0; i < board[0].length; i++) {
            if (!valid(row, i, board)) {
                continue;
            }
            board[row][i] = 'Q';

            backtrack(board, row + 1);

            board[row][i] = '.';
        }
    }

    // 如果将Q放置在[row, col]位置处是否合法
    private boolean valid(int row, int col, char[][] board) {
        // 同行有Q不行（这一个不写应该也行）
        for (int i = 0; i < board[0].length; i++) {
            if (board[row][i] == 'Q') {
                return false;
            }
        }
        // 同列有Q不行
        for (int i = 0; i <= row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        // 45度和135度斜线上有Q不行
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }
}
