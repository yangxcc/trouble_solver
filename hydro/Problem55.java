package hydro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1170
 * 
 * 问题描述：塔子哥是一位著名的冒险家，他经常在各种森林里探险。今天，他来到了道成林，这是一片美丽而神秘的森林。
 * 在探险途中，他遇到了一棵 n 个节点的树，树上每个节点都被涂上了红、绿、蓝三种颜色之一。
 * 塔子哥发现，如果这棵树同时存在一个红色节点、一个绿色节点和一个蓝色节点，那么我们就称这棵树是多彩的。
 * 很幸运，他发现这棵树最初就是多彩的。
 * 但是，在探险的过程中，塔子哥发现这棵树上有一条边非常重要，如果砍掉这条边，就可以把这棵树分成两个部分。
 * 他想知道，有多少种砍法可以砍掉这条边，使得砍完之后形成的两棵树都是多彩的。
 * 
 * 输入描述：第一行个整数 n ，表示节点个数
 * 第二行 n−1 个整数p2, p3, ...pn， pi表示树上 i 和 p 两点之间有一条边。保证给出的一定是一棵树。
 * 第三行一个长度为 n 的字符串，第 i 个字符表示第 i 个节点的初始颜色。其中 R 表示红色， G 表示绿色， B 表示蓝色。
 * 保证字符串只由这三种大写字母构成。
 * 对于全部数据， 3≤n≤10^5
 * 
 * 输出描述：输出一行，一个正整数，表示答案。
 */
public class Problem55 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        List<Integer>[] edges = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            int node = in.nextInt();
            edges[i + 1].add(node);
            edges[node].add(i + 1);
        }
        in.nextLine();

        String color = in.nextLine();
        dp = new int[n + 1][3];
        for (int i = 0; i < color.length(); i++) {
            if (color.charAt(i) == 'R') {
                dp[0][0]++;
            } else if (color.charAt(i) == 'G') {
                dp[0][1]++;
            } else {
                dp[0][2]++;
            }
        }

        dfs(edges, 1, 0, color);

        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        System.out.println(count);
        in.close();
    }

    // dp[i][0]表示以i为根的子树，红节点的个数
    static int[][] dp;
    static int count = 0;
    private static void dfs(List<Integer>[] edges, int cur, int pa, String color) {
        if (color.charAt(cur - 1) == 'R') {
            dp[cur][0]++;
        } else if (color.charAt(cur - 1) == 'G') {
            dp[cur][1]++;
        } else if (color.charAt(cur - 1) == 'B'){
            dp[cur][2]++;
        }

        for (int nei : edges[cur]) {
            if (nei == pa) {
                continue;
            }
            dfs(edges, nei, cur, color);
            dp[cur][0] += dp[nei][0];
            dp[cur][1] += dp[nei][1];
            dp[cur][2] += dp[nei][2];
        }

        if (dp[cur][0] > 0 && dp[cur][1] > 0 && dp[cur][2] > 0 &&
        dp[0][0] > dp[cur][0] && dp[0][1] > dp[cur][1] && dp[0][2] > dp[cur][2]) {
            count++;
        }
    }
}
