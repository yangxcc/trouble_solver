/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-27 17:35:57
 * @LastEditTime: 2023-01-20 16:17:32
 */
package tree.path_and_sum;

import java.util.ArrayList;
import java.util.List;

import tree.TreeNode;

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

        if (root.left == null && root.right == null) {
            ans.add(new ArrayList<>(path));
            return;
        }

        path.add(root.val);
        helper(root.left, path);
        helper(root.right, path);
    }
}