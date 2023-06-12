/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-06-12 10:46:23
 * @LastEditTime: 2023-06-12 15:42:11
 */
package hydro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1169
 * 
 * 问题描述：塔子哥是一个农民，他有一片 n×m 大小的田地，共 n 行 m 列，其中行和列都用从 1 开始的整数编号，
 * 田地中有 k 个格子中埋有土豆。我们记第 a 行第 b 列的格子为 (a,b) 。塔子哥现在位于 (x1,y1) ，他想要移动到
 * (x2,y2) 处去收菜，但是他不想阻碍自己土地里土豆的生长情况，所以他不想在移动过程中碰到土豆。
 * 
 * 塔子哥每次移动可以移动到与他所处格子的相邻的一格中，形式化地说，如果塔子哥位于 (x,y) ，则塔子哥可以移动到 (x−1,y) ，
 * (x+1,y) ，(x,y−1) ， (x,y+1) 的格子之一，但塔子哥不能移动到田地之外。
 * 塔子哥想要在移动过程中，离这些土豆越远越好，而不是走最短路径。这里定义两个格子之间的距离为曼哈顿距离，
 * 即格子 (a,b) 和 (c,d) 之间的距离是 ∣a−c∣+∣b−d∣ 。塔子哥想知道，移动中与土豆之间距离的最小值最大可能是多少。
 * 请注意，如果无论塔子哥如何移动，都会进入一个有土豆的格子的话，这个最大可能值为 0 。
 * 
 * 输入描述：第一行三个整数 n， m ，k ，分别表示田地的行数，列数和土豆个数。
 * 接下来 k 行，每行两个整数 p ， q ，表示一个土豆放置在格子 (p,q) 中。任意两土豆的放置位置不同。
 * 接下来一行四个整数 x1 ，y1，x2 ，y2 ，表示塔子哥的出发位置和目的位置。保证塔子哥的出发位置和目的位置上没有土豆。
 * 对于全部数据，
 * 1≤n,m≤500 ，n×m≥3，1≤k≤min{n×m−2,400} ，1≤p,x1,x2≤n ，1≤q,y1,y2≤m,(x1,y1)≠(2,y2)
 * ，
 * 保证 (x1,y1) 和 (x2,y2) 中没有土豆，并且一个格子中最多放置一个土豆。
 * 
 * 输出描述：输出一行一个整数，表示移动过程中与土豆之间距离的最小值的可能最大值。
 * 
 * 
 * 使用并查集，预处理所有点到土豆的最小值，然后从大到小排列，然后不断加入边，直到起点和终点相连，此时的边长就是最大值
 * 
 * 没能AC，10/12，代码看不出哪里有问题
 */
public class Problem54 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
        List<int[]> edges = new ArrayList<>();
        List<int[]> landmines = new ArrayList<>();
        while (k > 0) {
            landmines.add(new int[] { in.nextInt() - 1, in.nextInt() - 1 });
            k--;
        }
        int x1 = in.nextInt() - 1, y1 = in.nextInt() - 1, x2 = in.nextInt() - 1, y2 = in.nextInt() - 1;
        int p = x1 * m + y1 + 1, q = x2 * m + y2 + 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                edges.add(new int[] { i * m + j + 1, getDistance(i, j, landmines) });
            }
        }

        Collections.sort(edges, (o1, o2) -> o2[1] - o1[1]);

        for (int[] edge : edges) {
            System.out.printf("[%d, %d] \n", edge[0], edge[1]);
        }

        UF uf = new UF(n * m);
        int ans = 0;
        int dummy = 0;
        for (int i = 0; i < edges.size(); i++) {
            int point = edges.get(i)[0];
            int dis = edges.get(i)[1];
            uf.union(dummy, point);
            if (uf.connected(p, q)) {
                ans = dis;
                break;
            }
        }

        System.out.println(ans);

        in.close();
    }

    private static int getDistance(int i, int j, List<int[]> landmines) {
        int ans = Integer.MAX_VALUE;
        for (int[] landmine : landmines) {
            int dis = Math.abs(i - landmine[0]) + Math.abs(j - landmine[1]);
            if (dis == 0) {
                ans = 0;
            } else {
                ans = Math.min(dis, ans);
            }
        }

        return ans;
    }
}

class UF {
    private int[] parent;
    private int count;

    public UF(int _count) {
        this.parent = new int[_count + 1];
        for (int i = 0; i <= _count; i++) {
            parent[i] = i;
        }
        this.count = _count;
    }

    public boolean connected(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);

        return rootP == rootQ;
    }

    private int findRoot(int node) {
        if (node != parent[node]) {
            parent[node] = findRoot(parent[node]);
        }

        return parent[node];
    }

    public void union(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);

        if (rootP != rootQ) {
            parent[rootP] = rootQ;
            this.count--;
        }
    }

}
