/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-27 17:35:57
 * @LastEditTime: 2023-03-03 15:58:14
 */
package tree.path_and_sum;

import java.util.ArrayList;
import java.util.List;

import tree.TreeNode;

/**
 * leetcode 257 simple 二叉树的所有路径
 * 
 * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
 * 叶子节点 是指没有子节点的节点。
 */
public class AllPath {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> findAllPath(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }   

        helper(root, new ArrayList<>());

        return ans;
    }

    public void helper(TreeNode root, List<Integer> path) {
        if (root == null) {
            return;
        }

        path.add(root.val);
        
        if (root.left == null && root.right == null) {
            ans.add(new ArrayList<>(path));
            return;
        }

        helper(root.left, path);
        helper(root.right, path);
    }
}