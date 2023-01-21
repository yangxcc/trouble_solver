/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-21 14:47:56
 * @LastEditTime: 2023-01-21 15:02:31
 */
package tree.path_and_sum;

import java.util.ArrayList;
import java.util.List;

import tree.TreeNode;

/**
 * leetcode 113 middle 路径总和2
 * 
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * 叶子节点 是指没有子节点的节点。
 */
public class PathSum2 {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return new ArrayList<>();
        }
        process(root, targetSum, new ArrayList<>());
        return ans;
    }

    public void process(TreeNode root, int targetSum, List<Integer> path) {
        if (root == null) {
            return;
        }

        path.add(root.val);
        targetSum -= root.val;

        if (root.left == null && root.right == null && targetSum == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }

        process(root.left, targetSum, path);
        process(root.right, targetSum, path);

        path.remove(path.size()-1);
        targetSum += root.val;
    }
}
