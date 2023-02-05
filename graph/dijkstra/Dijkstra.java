package graph.dijkstra;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Arrays;

/**
 * dijkstra算法的目的是计算出一个节点到另一个节点的最短路径
 * 
 * 标准的dijkstra算法会把从起点start开始到所有其他节点的最短路径都算出来
 * 
 * dijkstra算法有一个要求：边权重不能为负数
 */
public class Dijkstra {

    // 从start到其他节点的最短路径
    public int[] dijkstra(int start, List<Integer>[] graph) {
        // 节点个数
        int n = graph.length;
        // 距离数组，distance[i]表示的是start到i的最短距离
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> {
            return a.distanceFromStart - b.distanceFromStart;
        });

        pq.add(new State(start, 0));

        while (!pq.isEmpty()) {
            State curNode = pq.poll();
            int curNodeID = curNode.id;
            int distanceBetweenCurAndStart = curNode.distanceFromStart;

            if (distanceBetweenCurAndStart > distance[curNodeID]) {
                continue;
            }

            for (int neightbor : graph[curNodeID]) {
                int distanceBetweenNeiAndStart = distance[curNodeID] + weight(curNodeID, neightbor);
                if (distance[neightbor] > distanceBetweenNeiAndStart) {
                    distance[neightbor] = distanceBetweenNeiAndStart;
                    pq.add(new State(neightbor, distanceBetweenNeiAndStart));
                }
            }
        }

        return distance;

    }

    // 返回节点a和节点b之间的边权重
    private int weight(int a, int b) {
        // 视具体题目中图结构来定
        return 0;
    }
}

class State {
    int id;
    int distanceFromStart;

    public State(int id, int distanceFromStart) {
        this.id = id;
        this.distanceFromStart = distanceFromStart;
    }
}