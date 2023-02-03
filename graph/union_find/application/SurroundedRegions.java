/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-02-03 11:53:55
 * @LastEditTime: 2023-02-03 18:30:13
 */
package graph.union_find.application;

/**
 * leetcode 130 middle 被围绕的区域
 * 
 * 这其实是岛问题，岛问题一般经常使用dfs来完成，但岛问题一般也能够通过并查集来解决
 * 
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X'
 * 填充。
 */
public class SurroundedRegions {
    public void solve(char[][] board) {
        int m = board.length, n = board[0].length;

        for (int i = 0; i < m; i++) {
            dfs(board, i, 0);
            dfs(board, i, n - 1);
        }

        for (int j = 0; j < n; j++) {
            dfs(board, 0, j);
            dfs(board, m - 1, j);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void dfs(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != 'O') {
            return;
        }

        board[i][j] = '#';

        dfs(board, i - 1, j);
        dfs(board, i + 1, j);
        dfs(board, i, j - 1);
        dfs(board, i, j + 1);
    }

    // 能用并查集，但是大材小用，对于岛问题使用dfs就行，这里只是展示一下怎么用并查集解决岛问题
    public void solveWithUF(char[][] board) {
        int m = board.length, n = board[0].length;
        UF uf = new UF(m * n + 1);
        // 将四周的 'O' 用一个虚拟节点dummy连起来
        int dummy = m * n; // dummy当做最后一个节点

        for (int i = 0; i < m; i++) {
            // 第一列上的 'O'
            if (board[i][0] == 'O') {
                uf.union(dummy, i * n);
            }
            // 最后一列上的 'O'
            if (board[i][n - 1] == 'O') {
                uf.union(dummy, i * n + n - 1);
            }
        }

        for (int j = 0; j < n; j++) {
            // 第一行上的 'O'
            if (board[0][j] == 'O') {
                uf.union(dummy, j);
            }

            // 最后一行上的 'O'
            if (board[m - 1][j] == 'O') {
                uf.union(dummy, n * (m - 1) + j);
            }
        }

        int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (board[i][j] == 'O') {
                    for (int k = 0; k < 4; k++) {
                        int x = dir[k][0] + i;
                        int y = dir[k][1] + j;

                        if (board[x][y] == 'O') {
                            uf.union(i * n + j, x * n + y);
                        }
                    }
                }
            }
        }

        // 所有没有和dummy连接的O都需要被替换
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (!uf.connect(dummy, i * n + j)) {
                    board[i][j] = 'X';
                }
            }
        }
    }
}
