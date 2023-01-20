/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-27 17:35:57
 * @LastEditTime: 2023-01-20 16:19:25
 */
package tree.path_and_sum;

import java.util.ArrayList;
import java.util.List;

import tree.TreeNode;

/**
 * leetcode 257 simple 二叉树的所有路径
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