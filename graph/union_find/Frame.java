/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-02-03 11:27:35
 * @LastEditTime: 2023-02-03 11:43:00
 */
package graph.union_find;

/**
 * 并查集框架的优化点
 * 1. 可以使用size数组来记录以i为根节点的树中有多少节点，在union的过程中总是小的挂到大的上面，这样能够避免树不平衡使得时间复杂度为O(n),即树的高度为n
 * 2. 路径压缩，因为同一棵树上的节点都有相同的根节点，因此在find的过程中，可以把所有的节点都挂到最开始的根节点上，这样做的目的
 *    是让树的高度保持一个常量，因此判断两个节点是否相连的时间复杂度为O(1)
 * 
 * 使用路径压缩，用不着size数组了
 * 
 */
public class Frame {
    // count的含义是连通分量的个数，连通分量指的是有几个部分没有互相连接
    private int count;
    private int[] parent;

    public Frame(int count) {
        this.count = count;
        parent = new int[count];
        // 每个节点最开始的父节点都是他们本身
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    /**
     * 两个节点是否相连
     * @param p
     * @param q
     * @return
     */
    public boolean connect(int p, int q) {
        int pRoot = findRoot(p);
        int qRoot = findRoot(q);

        return pRoot == qRoot;
    }

    /**
     * 将q和p连接起来，具体做法就是将一个节点的根节点挂到另一个节点的根节点的那里即可
     * @param p
     * @param q
     */
    public void union(int p, int q) {
        int pRoot = findRoot(p);
        int qRoot = findRoot(q);
        if (pRoot == qRoot) {
            return;
        }

        parent[pRoot] = qRoot;
        this.count--;
    }

    private int findRoot(int x) {
        // 最基本的写法，无路径压缩
        // while (x != parent[x]) {
        //     x = parent[x];
        // }

        // return x;

        // 一定要记住这里是if，第二遍看的时候还是把这里写成了while
        if (x != parent[x]) {
            parent[x] = findRoot(parent[x]);
        }

        return parent[x];

        // 上面的递归翻译成迭代代码为
        // 1. 找到root节点
        // int root = x;
        // while (root != parent[root]) {
        //     root = parent[root];
        // }
        // 2. 将每个节点都依次直接挂到根节点上
        // int oldParent = parent[x];
        // while (root != x) {
        //     parent[x] = root;
        //     x = oldParent;
        //     oldParent = parent[oldParent];
        // }
        // return root;
    }

    public int getCount() {
        return this.count;
    }
}