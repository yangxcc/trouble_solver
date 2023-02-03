package graph.union_find.application;

/**
 * leetcode 323 middle 无向图中连通分量的数目（会员题目）
 * 
 * 这道题很明显就是使用并查集来做，连通分量就是最后有几个部分没有连接，也就是并查集中count的数目
 * 
 * 给定编号从 0 到 n-1 的 n 个节点和一个无向边列表（每条边都是一对节点），请编写一个函数来计算无向图中连通分量的数目。
 *
    示例 1:

    输入: n = 5 和 edges = [[0, 1], [1, 2], [3, 4]]

        0          3
        |          |
        1 --- 2    4 

    输出: 2
    示例 2:

    输入: n = 5 和 edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

        0           4
        |           |
        1 --- 2 --- 3

    输出:  1
 */
public class ConnectedComponents {
    public int countComponents(int n, int[][] edges) {
        UF uf = new UF(n);
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }

        return uf.getCount();
    }
}

class UF {
    private int count;
    private int[] parent;

    public UF(int count) {
        this.count = count;
        parent = new int[count];

        for (int i = 0; i < count; i++) {
            parent[i] = i;
        }
    }

    public void union(int p, int q) {
        int pRoot = findRoot(p);
        int qRoot = findRoot(q);

        if (pRoot == qRoot) {
            return;
        }

        parent[pRoot] = qRoot;
        this.count--;
    }

    // 在本题中用不到
    public boolean connect(int p, int q) {
        int pRoot = findRoot(p);
        int qRoot = findRoot(q);

        return pRoot == qRoot;
    }

    public int findRoot(int x) {
        while (x != parent[x]) {
            parent[x] = findRoot(parent[x]);
        }

        return parent[x];
    }

    public int getCount() {
        return this.count;
    }
}
