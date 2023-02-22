/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-01 19:44:40
 * @LastEditTime: 2023-02-14 18:03:37
 */
package array.simulation_process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import linkedlist.ListNode;

/**
 * leetcode 59 螺旋矩阵II
 * 
 * 给你一个正整数 n ，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 */
public class SpiralMatrix {
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int up = 0, down = n - 1;
        int left = 0, right = n - 1;

        int num = 1;

        while (true) {
            for (int i = left; i <= right; i++) {
                ans[up][i] = num++;
            }
            up++;

            if (up > down) {
                break;
            }

            for (int i = up; i <= down; i++) {
                ans[i][right] = num++;
            }
            right--;

            if (left > right) {
                break;
            }

            for (int i = right; i >= left; i--) {
                ans[down][i] = num++;
            }
            down--;

            if (down < up) {
                break;
            }

            for (int i = down; i >= up; i--) {
                ans[i][left] = num++;
            }
            left++;

            if (left > right) {
                break;
            }
        }

        return ans;
    }

    /**
     * leetcode 885 middle 螺旋矩阵III
     * 具体题意得去平台上看，看一下例子才能明白
     * 
     * @param rows
     * @param cols
     * @param rStart
     * @param cStart
     * @return
     */
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int[][] ans = new int[rows * cols][2];
        int idx = 0;
        int[][] dir = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        int d = 0; // 方向

        int len = 1; // 记录这次方向上走多少步
        int l = 0;

        while (idx < rows * cols) {
            if (rStart < rows && rStart >= 0 && cStart < cols && cStart >= 0) {
                ans[idx++] = new int[] { rStart, cStart };
            }

            rStart += dir[d][0];
            cStart += dir[d][1];

            if (++l == len) {
                d++; // 方向动一下
                d %= 4; // 四次一循环
                if (d % 2 == 0) {
                    // 方向每动两次，在该方向上走的步数+1
                    len++;
                }

                l = 0; // l重置
            }
        }

        return ans;
    }

    /**
     * leetcode 2326 middle 螺旋矩阵IV
     * 给你两个整数：m 和 n ，表示矩阵的维数。
     * 另给你一个整数链表的头节点 head 。
     * 请你生成一个大小为 m x n 的螺旋矩阵，矩阵包含链表中的所有整数。
     * 链表中的整数从矩阵 左上角 开始、顺时针 按 螺旋 顺序填充。如果还存在剩余的空格，则用 -1 填充。
     * 返回生成的矩阵。
     * 
     * @param m
     * @param n
     * @param head
     * @return
     */
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] ans = new int[m][n];
        for (int[] row : ans) {
            Arrays.fill(row, -1);
        }

        int up = 0, down = m - 1;
        int left = 0, right = n - 1;
        int count = 0; // 避免链表比数组长

        while (true) {
            for (int i = left; i <= right && head != null && count < m * n; i++) {
                ans[up][i] = head.val;
                head = head.next;
                count++;
            }
            up++;
            if (up > down || head == null || count >= m * n) {
                break;
            }

            for (int i = up; i <= down && head != null && count < m * n; i++) {
                ans[i][right] = head.val;
                head = head.next;
                count++;
            }
            right--;
            if (left > right || head == null || count >= m * n) {
                break;
            }

            for (int i = right; i >= left && head != null && count < m * n; i--) {
                ans[down][i] = head.val;
                head = head.next;
                count++;
            }
            down--;
            if (up > down || head == null || count >= m * n) {
                break;
            }

            for (int i = down; i >= up && head != null && count < m * n; i--) {
                ans[i][left] = head.val;
                head = head.next;
                count++;
            }
            left++;
            if (left > right || head == null || count >= m * n) {
                break;
            }
        }

        return ans;
    }
}