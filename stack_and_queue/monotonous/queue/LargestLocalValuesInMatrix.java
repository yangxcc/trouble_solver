package stack_and_queue.monotonous.queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode 2373 simple 矩阵中的局部最大值
 * 给你一个大小为 n x n 的整数矩阵 grid 。
 * 生成一个大小为 (n - 2) x (n - 2) 的整数矩阵  maxLocal ，并满足：
 * maxLocal[i][j] 等于 grid 中以 i + 1 行和 j + 1 列为中心的 3 x 3 矩阵中的 最大值 。
 * 换句话说，我们希望找出 grid 中每个 3 x 3 矩阵中的最大值。
 * 返回生成的矩阵。
 */
public class LargestLocalValuesInMatrix {
    /**
     * 仅就这道题来说，还是很简单的，因为已经给我们确定了是一个3*3的范围，直接暴力循环也不会出现什么问题
     * 时间复杂度为O(n^2)
     */
    public int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        int[][] ans = new int[n - 2][n - 2];
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < n - 2; j++) {
                for (int x = i; x < i + 3; x++) {
                    for (int y = j; y < j + 3; y++) {
                        ans[i][j] = Math.max(ans[i][j], grid[x][y]);
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 但是如果不是让我们找3*3的范围，而是一个h*w的范围，且h*w比较大，那么我们使用暴力循环时间复杂度会升高，可能会接近O(n^4)
     * 我们做过滑动窗口的最大值这道题，所以可以借助单调队列来完成这道题目
     */
    public int[][] largestLocal(int[][] grid, int h, int w) {
        int m = grid.length;
        int n = grid[0].length;
        // 先计算出每一行的滑动窗口的最大值
        int[][] rowMax = new int[m][n - w + 1];
        for (int i = 0; i < m; i++) {
            MonotonousQueue queue = new MonotonousQueue();
            for (int j = 0; j < n; j++) {
                if (j < w - 1) {
                    // j从0开始，比如w=3，这里需要加入两个数
                    queue.push(grid[i][j]);
                } else {
                    queue.push(grid[i][j]);
                    rowMax[i][j - w + 1] = queue.getMax();
                    queue.pop(grid[i][j - w + 1]);
                }
            }
        }

        // 再去计算rowMax的每一列的滑动窗口最大值
        int[][] areaMax = new int[m - h + 1][n - w + 1];
        for (int j = 0; j < n - w + 1; j++) {
            MonotonousQueue queue = new MonotonousQueue();
            for (int i = 0; i < m; i++) {
                if (i < h - 1) {
                    queue.push(rowMax[i][j]);
                } else {
                    queue.push(rowMax[i][j]);
                    areaMax[i - h + 1][j] = queue.getMax();
                    queue.pop(rowMax[i - h + 1][j]);
                }
            }
        }
        return areaMax;
    }



    static class MonotonousQueue {
        Deque<Integer> queue;
        public MonotonousQueue() {
            this.queue = new LinkedList<>();
        }

        public void push(int x) {
            while (!queue.isEmpty() && queue.getLast() < x) {
                queue.removeLast();
            }
            queue.addLast(x);
        }

        public void pop(int x) {
            if (queue.getFirst() == x) {
                queue.removeFirst();
            }
        }

        public int getMax() {
            return queue.getFirst();
        }

        public int getSize() {
            return queue.size();
        }
    }
}
