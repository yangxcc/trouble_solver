package graph.minimun_spanning_tree.prim;

import java.util.ArrayList;
import java.util.List;

public class Leetcode1135 {
    public int minimumCost(int n, int[][] connection) {
        List<int[]>[] graph = buildGraph(n, connection);
        Prim prim = new Prim(graph);

        return prim.allConnected() ? prim.getMstWeight() : -1;
    }

    public List<int[]>[] buildGraph(int n, int[][] connection) {
        List<int[]>[] graph = new ArrayList[n];

        // 题目中给的节点编号是从1开始的
        for (int[] edge : connection) {
            int node1 = edge[0] - 1;
            int node2 = edge[1] - 1;
            int weight = edge[2];

            graph[node1].add(new int[]{node1, node2, weight});
            graph[node2].add(new int[]{node2, node1, weight});
        }

        return graph;
    }
}

/**
 * Leetcode 1584同样，就是得把二维坐标通过索引转成一维的
 */