/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-02-04 21:56:07
 * @LastEditTime: 2023-02-04 22:15:31
 */
package graph.minimun_spanning_tree.prim;

import java.util.List;
import java.util.PriorityQueue;

public class Prim {
    // 用来存储节点之间的边
    private PriorityQueue<int[]> q;
    // 节点是否已经被加入mst中，作用同换检测算法中的onPath
    private boolean[] visited;
    // 计算mst的权重
    private int mstWeight;
    // 图，[from, to, weight]，三元组
    private List<int[]>[] graph;

    public Prim(List<int[]>[] graph) {
        // 使用边的权重组织小根堆
        q = new PriorityQueue<>((a, b) -> {
            return a[2] - b[2];
        });

        int n = graph.length;
        this.visited = new boolean[n];
        this.mstWeight = 0;
        this.graph = graph;

        // 从任意一个节点开始
        visited[0] = true;
        addNeighbors(0);

        while (!q.isEmpty()) {
            int[] edge = q.poll();
            int to = edge[1];
            int weight = edge[2];
            if (visited[to]) {
                continue;
            }

            mstWeight += weight;
            visited[to] = true;
            addNeighbors(to);
        }
    }


    /**
     * 将节点x的邻边加入到优先级队列中
     * @param x
     */
    private void addNeighbors(int x) {
        for (int[] edge : graph[x]) {
            int to = edge[1];
            // 这表示这条边已经加入进入了，这种情况下就不要再加了
            if (visited[to]) {
                continue;
            }
            q.add(edge);
        }
    }
    

    public int getMstWeight() {
        return this.mstWeight;
    }

    public boolean allConnected() {
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
}
