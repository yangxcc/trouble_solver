/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-06-12 18:30:46
 * @LastEditTime: 2023-06-15 22:20:01
 */
package hydro;

import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1237
 * 
 * 问题描述：塔子哥所在的国家有 n 个城市，这 n 个城市排成一列，按顺序编号为 1,2,3,...,n。
 * 然而，由于历史原因和地理条件等多种原因，这些城市之间并没有相互连接的铁路，导致交通十分不便。
 * 为了改善这种情况，政府决定修建一些铁路来提高城市之间的交通效率。具体来说，政府计划在未来的 T 天内
 * 进行一系列铁路建设工作。每一天，政府会进行如下操作之一：
 * L x：在编号为 x 的城市和其左边的城市之间修建一条铁路，以便两个城市之间的交通更加便利。如果 x 已经位于最左边，或者 x 和它左边的城市之间已经存在铁路，则该操作无效。
 * R x：在编号为 x 的城市和其右边的城市之间修建一条铁路，以便两个城市之间的交通更加便利。如果 x 已经位于最右边，或者 x 和它右边的城市之间已经存在铁路，则该操作无效。
 * Q x：查询从编号为 x 的城市出发，最远能够到达的向左和向右的城市的编号。
 * 
 * 塔子哥需要编写一段程序来模拟这一系列操作，并及时输出每个 Q x 操作的结果。通过这个程序，政府可以更加高效地规划城市之间的交通网络，从而促进经济和社会的发展。
 * 
 * 输入描述：第一行输入两个正整数 n ， T ； 接下来 T 行，每行输入形如题面中的其中一种。
 * 1≤n≤10000 ， 1≤T≤200 ， 1≤x≤n
 * 
 * 输出描述：对于每一个Q x 操作，输出一行两个正整数，分别表示 x 往左边和往右边最远能到达的城市编号中间用空格隔开。
 * 
 */
public class Problem56 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), t = in.nextInt();
        UF uf = new UF(n);
        in.nextLine();
        while (t > 0) {
            String[] ele = in.nextLine().split(" ");
            int idx = Integer.parseInt(ele[1]);
            int left = idx, right = idx;
            if (ele[0].equals("Q")) {
                while (left >= 1 && uf.connected(left, idx)) {
                    left--;
                }
                while (right <= n && uf.connected(right, idx)) {
                    right++;
                }
                System.out.printf("%d %d\n", left + 1, right - 1);

            } else if (ele[0].equals("L")) {
                while (left > 1 && uf.connected(left, idx)) {
                    left--;
                }
                uf.union(idx, left);
            } else if (ele[0].equals("R")) {
                while (right < n && uf.connected(right, idx)) {
                    right++;
                }
                uf.union(idx, right);
            }
            t--;
        }

        in.close();
    }
}

class UF {
    private int count;
    private int[] parent;

    public UF(int _count) {
        this.parent = new int[_count + 1];
        this.count = _count;
    }

    public void union(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);

        if (rootP == rootQ) {
            return;
        }

        parent[rootP] = rootQ;
        this.count--;
    }

    private int findRoot(int x) {
        while (x != parent[x]) {
            parent[x] = findRoot(parent[x]);
        }

        return parent[x];
    }

    public boolean connected(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);

        return rootP == rootQ;
    }
}

/**
 * 这里又是没有看清题意要求，题目要求的是只会移动一格，如果这一个位置已经被联通了，不会再往下走了
 */
class RightWay {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), t = in.nextInt();
        // arr[i][0] != 0表示能往左走，arr[i][1]表示能往右走
        int[][] arr = new int[n + 2][2];
        while (t-- > 0) {
            String flag = in.next();
            int idx = in.nextInt();

            if (flag.equals("Q")) {
                int left = idx, right = idx;
                while (left >= 1 && arr[left][0] != 0) {
                    left--;
                }

                while (right <= n && arr[right][1] != 0) {
                    right++;
                }

                System.out.print(left + " " + right);
            } else if (flag.equals("L")) {
                if (idx == 1) {
                    continue;
                }
                arr[idx][0] = 1;
                arr[idx - 1][1] = -1; 
            } else {
                if (idx == n) {
                    continue;
                }
                arr[idx][1] = -1;
                arr[idx + 1][0] = 1;
            }
        }
        in.close();
    }
}