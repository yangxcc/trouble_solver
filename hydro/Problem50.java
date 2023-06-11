/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-06-11 11:26:36
 * @LastEditTime: 2023-06-11 11:41:35
 */
package hydro;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1141
 * 
 * 问题描述：塔子哥是一名计算机科学家，他正在研究一种新的数据结构——树。树是一种无向无环联通图，它由若干个节点和若干条边组成。
 * 每个节点都可以有零个或多个子节点，而每条边都连接两个节点。塔子哥现在有一棵树，树上的每个节点都有自己的价值。价值的计算规则如下所示：
 * 1. 若某节点 N 没有儿子节点，那么节点 N 价值为 1 ；
 * 2. 若某节点 N 有两个儿子节点，那么节点 N 价值为两个儿子节点的价值之和，或者价值之按位异或。这取决于节点 N 的颜色，
 * 若 N 的颜色为红色，那么节点 N 价值为两个儿子节点的价值之和；若 N 的颜色为绿色，那么节点 N 价值为两个儿子节点的价值之按位异或。
 * 3. 若某节点 N 只有一个儿子节点，则它等于这个唯一的儿子的权值
 *
 * 输入描述：第一行一个正整数 n 表示节点个数。
 * 第二行 n−1 个正整数 p[i] （2≤i≤n ）表示第 i 个节点的父亲。 1 号节点是根节点。
 * 第三行 n 个整数 c[i] （1≤i≤n)，当 c[i]=1 时表示第 i 个节点是红色， c[i]=2 则表示绿色。
 * 数据保证形成合法的树。对于所有的数据， n≤50000
 * 
 * 输出描述：输出一行一个整数表示根节点的值。
 */

class Problem50 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        // 长度为n-1
        int[] parents = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] colors = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        TreeNode[] nodes = new TreeNode[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new TreeNode(colors[i]);
        }
        for (int i = 0; i < n - 1; i++) {
            if (nodes[parents[i] - 1].left == null) {
                nodes[parents[i] - 1].left = nodes[i + 1];
            } else {
                nodes[parents[i] - 1].right = nodes[i + 1];
            }
        }

        System.out.print(getVal(nodes[0]));
        in.close();
    }

    private static int getVal(TreeNode cur) {
        if (cur == null) {
            return 0;
        }

        if (cur.left == null && cur.right == null) {
            return 1;
        }

        if (cur.left == null) {
            return getVal(cur.right);
        }
        if (cur.right == null) {
            return getVal(cur.left);
        }

        if (cur.color == 1) {
            return getVal(cur.left) + getVal(cur.right);
        } else {
            return getVal(cur.left) ^ getVal(cur.right);
        }
    }
}

class TreeNode {
    int color;
    TreeNode left;
    TreeNode right;

    public TreeNode(int color) {
        this.color = color;
    }
}
