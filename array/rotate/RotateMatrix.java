/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-04-01 21:35:29
 * @LastEditTime: 2023-04-01 21:49:05
 */
package array.rotate;

/**
 * leetcode 48 middle 旋转图像
 * 
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 * 
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 */
public class RotateMatrix {
    public void rotate(int[][] matrix) {
        // 先水平反转，再按照对角线反转
        levelRotate(matrix);
        diagonalRotate(matrix);
    }

    private void levelRotate(int[][] matrix) {
        int up = 0, down = matrix.length - 1;

        while (up < down) {
            for (int i = 0; i < matrix[0].length; i++) {
                int tmp = matrix[up][i];
                matrix[up][i] = matrix[down][i];
                matrix[down][i] = tmp;
            }
            up++;
            down--;
        }
    }

    public void diagonalRotate(int[][] matrix) {
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = 0; j < i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }
}
