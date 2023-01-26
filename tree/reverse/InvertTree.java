/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-24 20:49:16
 * @LastEditTime: 2023-01-26 09:23:08
 */
package tree.reverse;

import tree.TreeNode;

public class InvertTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        if (root.left == null && root.right == null) {
            return root;
        }
        
        TreeNode leftTmp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(leftTmp);

        return root;
    }
}
