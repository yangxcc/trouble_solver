package hydro;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1036
 * 
 * 问题描述：给你一棵n个节点的有根树，编号从 1 到 n ,根是 1 号节点。初始时，树上的每个节点都是红色。
 * 现在塔子哥需要你构造一种"子树蓝奇"状态:这棵树的以任意一个节点为根的子树内蓝色节点的个数是奇数个。
 * 但这样的方案可太多了，所以塔子哥要求你找到蓝色节点数最多的"子树蓝奇"状态,并输出这棵树。
 * 
 * 输入描述：第一行输入一个正整数 n 。
 * 接下来 n−1 行，每行两个正整数 u,v ,表示 u 号节点和 v 号节点之间有一条边。1<n<10^5，1≤u,v≤n
 * 
 * 输出描述：输出一个长度为 n 的字符串，表示染色后的树。如果第个字符是'R',代表树上的 i 号节点是红色;
 * 如果个字符是'B',则表示树上的第 i 号节点是蓝色。
 * 
 * 
 * 思路：以任意节点为根的子树蓝色节点的个数都是奇数个，能够知道叶子节点都得是蓝色的
 * 所以对于任意一个节点，我们需要看一下它子树中蓝色节点的数量，
 * 如果蓝色节点是偶数，那么该节点需要给染成蓝色，如果蓝色节点是奇数，那么该节点需要染成红色
 * 
 * leetcode上还有两道染色问题，分别是34、52
 */
public class Problem34 {
    static List<Integer>[] trees;
    static int[] count;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        trees = new ArrayList[n + 1];
        count = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            trees[i] = new ArrayList<>();
        }

        in.nextLine();
        for (int i = 1; i < n; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            trees[u].add(v);
            trees[v].add(u);
        }  

        dfs(1, 0);
        for (int i = 1; i <= n; i++) {
            if (count[i] == 1) {
                System.out.print("B");
            } else {
                System.out.println("R");
            }
        }
        in.close();
    }

    /**
     * 以cur为当前根节点的子树中包含多少的蓝色节点
     * @param cur
     * @return
     */
    private static int dfs(int cur, int parent) {
        int sum = 0;
        for (int i = 0; i < trees[cur].size(); i++) {
            int child = trees[cur].get(i);
            if (child == parent) {
                continue;
            }

            sum += dfs(child, cur);
        }

        if (sum % 2 == 0) {
            // 给当前节点染色
            count[cur] = 1;
            return sum + 1;
        } else {
            // 是奇数，那就不染色了，就这样就行
            return sum;
        }
    }
}
