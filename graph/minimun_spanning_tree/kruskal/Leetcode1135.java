/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-02-04 16:37:19
 * @LastEditTime: 2023-02-04 16:55:37
 */
package graph.minimun_spanning_tree.kruskal;

/**
 * leetcode 1135 middle 最低成本联通所有城市
 * 
 * 最小生成树的定义就是 所有可能的生成树中，权值和最小的那一个
 * 
 * kruskal算法需要借助并查集来实现
 */
public class Leetcode1135 {
    /**
     * 
     * @param n 从1开始
     * @param connection  [[from, to, weight]...]
     * @return
     */
    public int minimumCost(int n, int[][] connection) {
        UF uf = new UF(n + 1);
        int mst = 0;

        for (int[] edge : connection) {
            int from = edge[0];
            int to = edge[1];
            
            if (uf.connected(from, to)) {
                continue;
            }

            uf.union(from, to);
            mst += edge[2];
        }

        // 根据题目中的要求，城市编号是从1开始的，所以最后剩下的连通分量是 0 和 最小生成树（如果有的话）
        return uf.getCount() == 2 ? mst : -1;
    }
}

class UF {
    private int count;
    private int[] parent;

    public UF(int c) {
        this.count = c;
        this.parent = new int[c];
    }

    public boolean connected(int p, int q) {
        int pRoot = findRoot(p);
        int qRoot = findRoot(q);

        return pRoot == qRoot;
    }

    public void union(int p, int q) {
        int pRoot = findRoot(p);
        int qRoot = findRoot(q);

        if (pRoot == qRoot) {
            return;
        }

        this.parent[pRoot] = qRoot;
        this.count--;
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
