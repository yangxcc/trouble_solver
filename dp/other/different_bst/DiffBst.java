package dp.other.different_bst;

import java.util.Scanner;

/**
 * leetcode 96 middle 不同的二叉搜索树
 * 
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？
 * 返回满足题意的二叉搜索树的种数。
 */
public class DiffBst{
    public int numTrees(int n) {
        return dp(n);
    }

    // 1-n这些数能够构成多少二叉树
    public int dp(int n) {
        if (n == 0) {
            return 1;
        }
        if (n <= 2) {
            return n;
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            // 以i为根
            ans += dp(i - 1) * dp(n - i);
        }

        return ans;
    }
}

