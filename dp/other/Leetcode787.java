package dp.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode 787 middle K站中转内最便宜的航班
 * 
 * 有 n 个城市通过一些航班连接。给你一个数组 flights ，其中 flights[i] = [fromi, toi, pricei] ，
 * 表示该航班都从城市 fromi 开始，以价格 pricei 抵达 toi。
 * 
 * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，
 * 你的任务是找到出一条最多经过 k 站中转的路线，使得从 src 到 dst 的 价格最便宜 ，
 * 并返回该价格。 如果不存在这样的路线，则输出 -1。
 * 
 * 
 * 能够使用dijkstra做，见graph/dijkstra/Leetcode787.java
 */
public class Leetcode787 {
    List<int[]>[] graph;
    int[][][] memo;
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        graph = buildGraph(n, flights);
        memo = new int[n][n][k + 2];
        for (int[][] xx : memo) {
            for (int[] x : xx) {
                Arrays.fill(x, -666);
            }
        }
        // 这里注意要是k+1，因为能够中转一次，相当于有三个点，两条边
        return dp(src, dst, k + 1);
    }

    /**
     * 该函数表示的是从i开始，在k步之内到dst的最短路径
     * @param i
     * @param dst
     * @param k
     * @return
     */
    public int dp(int i, int dst, int k) {
        if (i == dst) {
            return 0;
        }

        // 没有路线可走了，根据题意这里得返回-1，不能返回0
        if (k <= 0) {
            return -1;
        }

        if (memo[i][dst][k] != -666) {
            return memo[i][dst][k];
        }

        int res = Integer.MAX_VALUE;
        // 看看i有哪些邻居
        for (int[] edge : graph[i]) {
            int next = edge[0];
            int wei = edge[1];
            
            int subProblem = dp(next, dst, k - 1);
            if (subProblem != -1) {
                res = Math.min(res, subProblem + wei);
            }
        }

        memo[i][dst][k] = res == Integer.MAX_VALUE ? -1 : res;
        return memo[i][dst][k];
    }

    public List<int[]>[] buildGraph(int n, int[][] flights) {
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : flights) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];

            graph[from].add(new int[]{to, weight});
        }
        return graph;
    }
}
