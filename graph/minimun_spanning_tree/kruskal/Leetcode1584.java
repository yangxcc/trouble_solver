package graph.minimun_spanning_tree.kruskal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * leetcode 1584 middle 连接所有点的最小费用（会员题，1135也是会员题目）
 * 
 * 这里题目的要求 点是在二维坐标上，即[x,y]，两个点之间的距离是曼哈顿距离，即|x1 - x2| + |y1 - y2|
 */
public class Leetcode1584 {
    /**
     * 
     * @param points [[x1, y1], [x2, y2]...] ，节点的集合
     * @return
     */
    public int minCostConnectPoints(int[][] points) {
        List<int[]> edges = new ArrayList<>();
        UF uf = new UF(points.length);
        int mst = 0;

        // 将全部的边构造出来
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];

                // 这里其实是一个技巧，为了适配并查集的写法，我们使用节点的索引来代替坐标
                // 否则每个数组里应该应该包含五个元素
                edges.add(new int[] { i, j, Math.abs(x1 - x2) + Math.abs(y1 - y2) });
            }
        }

        // 按照边的权值从小到大排序
        Collections.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[2] - b[2];
            }
        });

        // Collections.sort(edges, (a, b) -> {
        // return a[2] - b[2];
        // });

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];

            if (uf.connected(from, to)) {
                continue;
            }

            uf.union(from, to);
            mst += weight;
        }

        return mst;        
    }
}
