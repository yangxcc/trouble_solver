package graph.bipartite_graph;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 886 middle 可能的二分法
 * 
 * 给定一组 n 人（编号为 1, 2, ..., n）， 我们想把每个人分进任意大小的两组。
 * 每个人都可能不喜欢其他人，那么他们不应该属于同一组。
 * 给定整数 n 和数组 dislikes ，其中 dislikes[i] = [ai, bi] ，表示不允许将编号为 ai 和 bi的人归入同一组。
 * 当可以用这种方法将所有人分进两组时，返回 true；否则返回 false。
 */

// 和判断二分图是一样的，只需要注意索引是从1开始的就好了以及dislikes的定义是给出了边，而不是给出了i节点的邻居节点
public class PossibleBipartition {
    boolean[] visited;
    boolean[] color;
    boolean ans;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        visited = new boolean[n + 1];
        color = new boolean[n + 1];
        ans = true;

        List<Integer>[] graph = buildGraph(dislikes, n);

        for (int i = 1; i <= n; i++) {
            dfs(graph, i);
        }

        return ans;
    }

    public void dfs(List<Integer>[] graph, int start) {
        if (visited[start] || !ans) {
            return;
        }

        visited[start] = true;

        for (int neighbor : graph[start]) {
            if (visited[neighbor]) {
                if (color[neighbor] == color[start]) {
                    ans = false;
                    return;
                }
            } else {
                color[neighbor] = !color[start];
                dfs(graph, neighbor);
            }
        }
    }

    public List<Integer>[] buildGraph(int[][] dislikes, int n) {
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 无向图相当于双向图
        for (int[] edge : dislikes) {
            int from = edge[0];
            int to = edge[1];
            graph[from].add(to);
            graph[to].add(from);
        }

        return graph;
    }
}
