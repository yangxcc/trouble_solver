/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-06-11 14:29:14
 * @LastEditTime: 2023-06-11 14:47:51
 */
package hydro;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1167
 * 
 * 问题描述：塔子哥发现这棵树有 n 个节点，其中有一条边被特别标记了出来。他开始思考这条特殊的边在树上起到了什么样的作用，
 * 于是他想知道，经过这条选定边的所有树上简单路径中，最长的那条路径有多长，以便更好地理解这棵树的结构。
 * 一条简单的路径的长度指这条简单路径上的边的个数。
 * 
 * 输入描述：第一行一个整数 n ，表示树的节点个数。
 * 第二行 n−1 个整数，第 i 个数 pi表示节点 i+1 和 pi之间有一条边相连。
 * 第三行两个整数 x ，y ，表示这条选定的边。保证这条边一定是树上的一条边。
 * 对于全部数据， 2≤n≤10^5, 1≤pi≤n ，1≤x,y≤n, x≠y
 * 保证输入数据正确描述一棵树，并且( x,y ) 是树上的条边。
 * 
 * 输出描述：输出一行，一个整数，表示所有经过选定边的树上简单路径中，最长的那条的长度。
 */
public class Problem52 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Integer>[] edges = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }
        in.nextLine();
        for (int i = 1; i <= n; i++) {
            int node = in.nextInt();
            edges[i + 1].add(node);
            edges[node].add(i + 1);
        }

        in.nextLine();
        int node1 = in.nextInt(), node2 = in.nextInt();
        System.out.println(dfs(edges, node1, node2) + dfs(edges, node2, node1) + 1);
        in.close();
    }

    /**
     * 以当前节点为起点，最长的路径
     */
    private static int dfs(List<Integer>[] edges, int cur, int parent) {
        int ans = 0;
        for (int nei : edges[cur]) {
            if (nei == parent) {
                continue;
            }
            ans = Math.max(ans, dfs(edges, nei, cur) + 1);
        }

        return ans;
    }
}
