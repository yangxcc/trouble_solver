/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-03-23 13:39:18
 * @LastEditTime: 2023-03-23 14:18:01
 */
package skill.collect_rain;

import java.util.PriorityQueue;

/**
 * leetcode 407 接雨水2 hard
 * 给你一个 m x n 的矩阵，其中的值均为非负整数，
 * 代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
 * 
 * https://leetcode.cn/problems/trapping-rain-water-ii/solutions/1081138/gong-shui-san-xie-jing-dian-dijkstra-yun-13ik/
 */
public class TrappingRainWater4 {
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length, n = heightMap[0].length;
        PriorityQueue<State> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.height - o2.height;
        });
        boolean[][] visited = new boolean[m][n];

        // 先把边界都加进去
        for (int i = 0; i < m; i++) {
            pq.add(new State(i, 0, heightMap[i][0]));
            pq.add(new State(i, n - 1, heightMap[i][n - 1]));
            visited[i][0] = true;
            visited[i][n - 1] = true;
        }

        for (int i = 0; i < n; i++) {
            pq.add(new State(0, i, heightMap[0][i]));
            pq.add(new State(m - 1, i, heightMap[m - 1][i]));
            visited[0][i] = true;
            visited[m - 1][i] = true;
        }

        int ans = 0;

        // 之所以使用优先级队列，是因为我们需要的是一个点(x,y)四周最矮的高度
        int[][] dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        while (!pq.isEmpty()) {
            State cur = pq.poll();
            for (int[] dir : dirs) {
                int neighborX = cur.x + dir[0];
                int neighborY = cur.y + dir[1];
                if (neighborX < 0 || neighborY < 0 || neighborX >= m || neighborY >= n
                        || visited[neighborX][neighborY]) {
                    continue;
                }
                if (cur.height > heightMap[neighborX][neighborY]) {
                    ans += cur.height - heightMap[neighborX][neighborY];
                }
                pq.add(new State(neighborX, neighborY, Math.max(cur.height, heightMap[neighborX][neighborY])));
                visited[neighborX][neighborY] = true;
            }
        }

        return ans;
    }

    class State {
        int x;
        int y;
        int height;

        public State(int _x, int _y, int h) {
            this.x = _x;
            this.y = _y;
            this.height = h;
        }
    }
}
