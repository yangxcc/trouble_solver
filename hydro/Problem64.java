package hydro;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 阿里淘天0824 笔试第三题
 * <p>
 * 小红有一棵传送树，树上有 n 个节点，编号为1到n，其中1号节点为根节点。
 * 每个节点都有一个传送门，传送门只可以将小红传送到子树中除了自己的其他节点中编号最小的节点。
 * 小红想知道，经过若干次传送门直到叶子节点为止，可以到达的节点数量是多少。
 * 输入描述：一行一个整数n，表示树上的节点数量。
 * 接下来n-1行，每行两个整数u,v，表示u号节点和号节点之间有一条边。
 * 输出描述：输出一行，n个整数，分别对树上n 个节点都需要计算答案
 * 第i个数字表示小红从第i个节点出发，经过若干次传送门到达叶子节点为止，可以到达的节点数量是多少。
 * <p>
 * 输入
 * 5
 * 1 4
 * 4 2
 * 4 3
 * 3 5
 * <p>
 * 输出
 * 2 1 2 2 1
 */
public class Problem64 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ans = new int[n + 1];
        graph = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int i = 1; i < n; i++) {
            int node1 = in.nextInt();
            int node2 = in.nextInt();
            graph[node1].add(node2);
            graph[node2].add(node1);
        }

        dp(1, 0);
        for (int i = 1; i <= n; i++) {
            System.out.printf("%d ", ans[i]);
        }

        in.close();
    }

    static List<Integer>[] graph;
    static int[] ans;

    // 树形dp，返回值有两个：第一个是以cur为根节点的子树中的最小节点
    // 第二个是以cur为起点，到达叶子节点遍历的节点的数量
    private static int[] dp(int cur, int parent) {
        if (cur != 1 && graph[cur].size() == 1 && graph[cur].get(0) == parent) {
            // 是叶子节点
            ans[cur] = 1;
            return new int[]{cur, 1};
        }

        int[] res = new int[]{Integer.MAX_VALUE, 0};
        for (int nei : graph[cur]) {
            if (nei == parent) {
                continue;
            }

            int[] tmp = dp(nei, cur);
            int minNode = tmp[0];
            int step = tmp[1];
            if (minNode < res[0]) {
                res[0] = minNode;
                if (minNode == nei) {
                    res[1] = step + 1;
                } else {
                    res[1] = step;
                }
            }
        }

        // 最小节点也有可能是自己
        res[0] = Math.min(res[0], cur);
        ans[cur] = res[1];
        return res;
    }
}
