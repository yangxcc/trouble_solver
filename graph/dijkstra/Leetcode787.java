package graph.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

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
 * 这道题目和其他题目不同的是，限制了中转次数，即限制了树的层高
 */
public class Leetcode787 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[src] = 0;

        int[] step = new int[n]; // 用来记录起点到当前节点的中转次数
        step[src] = 0;

        List<int[]>[] graph = buildGraph(n, flights);
        PriorityQueue<Station> pq = new PriorityQueue<>(
            (a, b) -> {
                return a.distanceBetweenCurAndStart - b.distanceBetweenCurAndStart;
            }
        );
        pq.offer(new Station(src, 0, 0));
        
        
        while (!pq.isEmpty()) {
            Station curNode = pq.poll();
            int curNodeIdx = curNode.id;
            int distanceFromStart = curNode.distanceBetweenCurAndStart;
            int stopCount = curNode.stepFromStart;

            // if (distanceFromStart > distance[curNodeIdx]) {
            //     //花费更多就立马跳过这条航线是错误的局限的
            //     //因为还有K的限制
            //     //有可能花费更少的路线但是中转次数不符合要求
            //     //最后就会输出-1
            //     continue;
            // }

            // 这段还必须放到前面
            if (curNodeIdx == dst) {
                return distanceFromStart;    
            }

            if (stopCount > k) {
                continue;
            }

            // if (curNodeIdx == dst) {
            //     return distanceFromStart;    
            // }

            for (int[] neighbor : graph[curNodeIdx]) {
                int neighborIdx = neighbor[0];
                int disBetweenCurAndNei = neighbor[1];

                int disBetweenStartAndNei = distanceFromStart + disBetweenCurAndNei;
                int stopCountFromStart = stopCount + 1;

                if (distance[neighborIdx] > disBetweenStartAndNei) {
                    distance[neighborIdx] = disBetweenStartAndNei;

                    step[neighborIdx] = stopCountFromStart;
                    // 不能仅仅通过距离来判断是否加入队列，因为小的距离有可能中转次数多，可能会导致中转次数超过k
                    // pq.offer(new State(neighborIdx, disBetweenStartAndNei));
                }

                // 对于花费更多，中转次数更多的跳过去
                if (distance[neighborIdx] < disBetweenStartAndNei && stopCountFromStart > step[neighborIdx]) {
                    continue;
                }

                pq.offer(new Station(neighborIdx, disBetweenStartAndNei, stopCountFromStart));
            }
        }

        return -1;
    }

    private List<int[]>[] buildGraph(int n, int[][] edges) {
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];
            int dis = edge[2];
            graph[node1].add(new int[]{node2, dis});
        }

        return graph;
    }
}

class Station {
    int id;
    int distanceBetweenCurAndStart;
    int stepFromStart;  // 从start到当前节点中转了几次

    public Station(int i, int d, int s) {
        this.id = i;
        this.distanceBetweenCurAndStart = d;
        this.stepFromStart = s;
    }
}