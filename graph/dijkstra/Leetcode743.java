/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-02-05 11:14:23
 * @LastEditTime: 2023-02-05 12:04:40
 */
package graph.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * leetcode 743 middle 网络延迟时间
 * 
 * 有 n 个网络节点，标记为 1 到 n。
 * 给你一个列表 times，表示信号经过 有向 边的传递时间。 
 * times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
 * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
 */
public class Leetcode743 {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<int[]>[] graph = buildGraph(times, n);
        int ans = -1;

        int[] distance = dijkstra(graph, k - 1);
        for (int i = 0; i < distance.length; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                return -1;
            } 
            ans = Math.max(ans, distance[i]);
        }
        return ans;
    }

    public int[] dijkstra(List<int[]>[] graph, int start) {
        int n = graph.length;
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        
        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> {
            return a.distanceFromStart - b.distanceFromStart;
        });
        pq.offer(new State(start, 0));

        while (!pq.isEmpty()) {
            State curNode = pq.poll();
            int curNodeID = curNode.id;
            int distanceBetweenCurAndStart = curNode.distanceFromStart;

            if (distanceBetweenCurAndStart > distance[curNodeID]) {
                continue;
            }

            for (int[] edges : graph[curNodeID]) {
                int neighbor = edges[1];
                int weight = edges[2];

                int distanceBetweenNeiAndStart = distanceBetweenCurAndStart + weight;

                if (distanceBetweenNeiAndStart < distance[neighbor]) {
                    distance[neighbor] = distanceBetweenNeiAndStart;
                    pq.add(new State(neighbor, distanceBetweenNeiAndStart));
                }
            }
        }

        return distance;
    }

    public List<int[]>[] buildGraph(int[][] times, int n) {
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : times) {
            int from = edge[0] - 1;
            int to = edge[1] - 1;
            int weight = edge[2];

            graph[from].add(new int[]{from, to, weight});
        }

        return graph;
    }
}
