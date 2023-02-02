package graph.bipartite_graph;

/**
 * leetcode 785 middle 判断是否为二分图
 * 
 * 二分图的定义：如果能将一个图的节点集合分割成两个独立的子集 A 和 B ，
 * 并使图中的每一条边的两个节点一个来自 A 集合，一个来自 B 集合，就将这个图称为 二分图 。
 * 
 * 
 * 其实 二分图的判断过程就是图染色的过程，一条边的两个点分别染成不同的颜色，如果最终所有的点能够染成两种不同的颜色，那么该图便是二分图
 */
public class IsBipartiteGraph {
    boolean[] visited;
    boolean[] color;
    boolean ans;

    public boolean isBipartite(int[][] graph) {
        visited = new boolean[graph.length];  // 题目中说了
        color = new boolean[graph.length];    // 记录图中节点的颜色
        ans = true; // 初始化为true
        
        // 图有可能不连通
        for (int i = 0; i < graph.length; i++) {
            dfs(graph, i);
        }

        return ans;
    }

    public void dfs(int[][] graph, int start) {
        if (visited[start]) {
            return;
        }

        visited[start] = true;
        for (int neighbor : graph[start]) {
            if (visited[neighbor]) {
                // 如果邻居节点已经访问过了，判断颜色是否相同
                if (color[start] == color[neighbor]) {
                    ans = false;
                    return;
                }
            } else {
                // 尚未访问过该节点，给该节点染色，边的两点要染不同的颜色
                color[neighbor] = !color[start];
                dfs(graph, neighbor);
            }

        }
    }
}
