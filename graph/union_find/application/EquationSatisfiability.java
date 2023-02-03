package graph.union_find.application;

import java.util.Arrays;
import java.util.HashSet;

/**
 * leetcode 990 middle 等式方程的可满足性
 * 
 * 给你⼀个数组 equations，装着若干字符串表示的算式。每个算式 equations[i] ⻓度都是 4，⽽且只有
 * 这两种情况：a==b 或者 a!=b，其中 a,b 可以是任意⼩写字母。你写⼀个算法，如果 equations 中所有算
 * 式都不会互相冲突，返回 true，否则返回 false。
 * ⽐如说，输⼊ ["a==b","b!=c","c==a"]，算法返回 false，因为这三个算式不可能同时正确。
 * 再⽐如，输⼊ ["c==c","b==d","x!=z"]，算法返回 true，因为这三个算式并不会造成逻辑冲突。
 */
public class EquationSatisfiability {
    public boolean equationsPossible(String[] equations) {
        // 最多有26个字母
        UF uf = new UF(26);

        for (String eq : equations) {
            if (eq.charAt(1) == '=') {
                uf.union(eq.charAt(0) - 'a', eq.charAt(3) - 'a');
            }
        }

        for (String eq : equations) {
            if (uf.connect(eq.charAt(0) - 'a', eq.charAt(3) - 'a')) {
                return false;
            }
        }

        return true;
    }
}

/**
 * 从这道题目中也能够看出来，并查集同样能够应用到 环检测中
 * 
 * 即在每次union之前，判断二者是否已经连接上了，每出现一次连接两个已经连通的顶点，图中就会多一个环
 * 
 * 比如有一个题目https://atcoder.jp/contests/abc226/tasks/abc226_e，题意大致为
 *  给定一个无向图（不一定联通），给边设定方向，使得所有的点出度都为 1，求有多少种方案
 * 
 * 我们但看这个无向图中的任意一个连通块，如果要求所有点的出度为1，那么必定这个连通块中有且仅有1个环
 * 因为如果有多个环，那么必定存在节点的出度大于1
 * 而且边的方向只有两个，因此假设连通块的个数为n，那么方案的个数为 2^n，因此只需要使用并查集得到环的个数，便能够得到连通图的个数
 * 
 * 注意：对于无向图来说，求环的个数需要先把边去一下重，比如 [0,1]和[1,0]是一样的
 */

class DetectCycle {
    /**
     * 给你输入编号从0到n - 1的n个结点，和一个无向边列表edges（每条边用节点二元组表示）
     * 请你判断输入的这些边组成的结构是否是一棵树。
     * @param n
     * @param edges
     * @return
     */
    public boolean validTree(int n, int[][] edges) {
        UF uf = new UF(n);
        for (int[] edge : edges) {
            // 没有处理[1,0]和[0,1]这种问题
            if (uf.connect(edge[0], edge[1])) {
                return false;
            }

            uf.union(edge[0], edge[1]);
        }

        return uf.getCount() == 1;  // 如果能够构成树，最后只会剩下一个联通向量
        // return true;  // 森林
    }

    // TODO 二维数组去重怎么做
    public static void main(String[] args) {
        HashSet<String> hs = new HashSet<>();

        int[] a = new int[]{1,0};
        int[] b = new int[]{0,1};

        Arrays.sort(a);

        System.out.println(Arrays.equals(a, b));
        System.out.println(hs.size());
        System.out.println(hs.toString());
    }
}
