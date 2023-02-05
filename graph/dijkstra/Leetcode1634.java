package graph.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Leetcode1634 {
    /**
     * 因为是二维坐标，这里可以修改一下dijkstra的模板，也可以将二维坐标映射到一维
     * 
     * @param heights
     * @return
     */
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        return dijkstra(heights, m - 1, n - 1);
    }

    // 原点到[x,y]的最短距离
    public int dijkstra(int[][] matrix, int x, int y) {
        int m = matrix.length, n = matrix[0].length;
        // distance[i][j]表示的是从原点[0,0]到[i,j]的路径中的距离最大值
        int[][] distance = new int[m][n];
        for (int[] d : distance) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        distance[0][0] = 0;

        PriorityQueue<State2D> pq = new PriorityQueue<>((a, b) -> {
            return a.distanceFromStart - b.distanceFromStart;
        });
        pq.add(new State2D(0, 0, 0));

        while (!pq.isEmpty()) {
            State2D curNode = pq.poll();
            int curNodeX = curNode.x;
            int curNodeY = curNode.y;
            int distanceBetweenCurAndStart = curNode.distanceFromStart;

            if (distanceBetweenCurAndStart > distance[curNodeX][curNodeY]) {
                continue;
            }

            if (curNodeX == x && curNodeY == y) {
                return distanceBetweenCurAndStart;
            }

            for (int[] edges : getNeighbors(matrix, curNodeX, curNodeY)) {
                int neiX = edges[0];
                int neiY = edges[1];
                int distanceBetweenNeiAndCur = edges[2];

                int dis = Math.max(distanceBetweenNeiAndCur, distance[curNodeX][curNodeY]);
                if (distance[neiX][neiY] > dis) {
                    distance[neiX][neiY] = dis;
                    pq.add(new State2D(neiX, neiY, dis));
                }
            }

        }
        return -1;
    }

    int[][] directions = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    private List<int[]> getNeighbors(int[][] graph, int x, int y) {
        List<int[]> neis = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int p = x + directions[i][0];
            int q = y + directions[i][1];
            if (p < 0 || q < 0 || p >= graph.length || q >= graph[0].length) {
                continue;
            }
            neis.add(new int[] { p, q, Math.abs(graph[x][y] - graph[p][q]) });
        }

        return neis;
    }
}

class State2D {
    int x;
    int y;
    int distanceFromStart;

    public State2D(int i, int j, int d) {
        this.x = i;
        this.y = j;
        this.distanceFromStart = d;
    }
}

/**
 * 此题还能够使用并查集，kruskal算法的实现
 * 因为kruskal算法实现过程中需要先对边的权重进行排序，所以边是越来越大的，当第一个点和最后一个点连通的时候，此时的权重便是答案
 */