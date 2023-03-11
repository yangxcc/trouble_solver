/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-02-01 14:05:36
 * @LastEditTime: 2023-03-11 15:12:38
 */
package tree.bst;

import java.util.ArrayList;
import java.util.List;

import tree.TreeNode;

/**
 * leetcode 96 middle 不同的二叉搜索树
 * 
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 */
public class DifferenceBST {
    int[][] memo;
    public int numTrees(int n) {
        // // 后面有+1操作
        // memo = new int[n+2][n+2];
        // return process(1, n);
        // dp[i]表示的是i个节点能够构成bst的个数
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    /**
     * [left,right]这个区间内能够构成的BST的数量
     * 
     * @param left
     * @param right
     * @return
     */
    public int process(int left, int right) {
        // if (left > right) {
        //     return 0;
        // }
        if (memo[left][right] != 0) {
            return memo[left][right];
        }
        // 空区间能够构成的树是null，也可以当成一个树
        if (left >= right) {
            return 1;
        }

        int res = 0;
        for (int i = left; i <= right; i++) {
            // 分别以i为根
            res += process(left, i - 1) * process(i + 1, right);
        }

        memo[left][right] = res;

        return res;
    }


    /**
     * leetcode 95 middle 不同的二叉搜索树II
     * 
     * 将所有不同的二叉搜索树返回
     */
    public List<TreeNode> generateTrees(int n) {
        return generate(1, n);
    }

    public List<TreeNode> generate(int left, int right) {
        List<TreeNode> ans = new ArrayList<>();

        if (left > right) {
            ans.add(null);
            return ans;
        }

        for (int i = left; i <= right; i++) {
            List<TreeNode> leftTree = generate(left, i - 1);
            List<TreeNode> rightTree = generate(i + 1, right);
            for (TreeNode leftNode : leftTree) {
                for (TreeNode rightNode : rightTree) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftNode;
                    root.right = rightNode;

                    ans.add(root);
                }
            }
        }

        return ans;
    }

}
