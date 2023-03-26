/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-11-02 15:33:41
 * @LastEditTime: 2023-03-26 11:03:52
 */
package tree.ancestor;

import java.util.HashMap;
import java.util.HashSet;

import tree.TreeNode;

/**
 * leetcode 剑指 Offer 68 - II. 二叉树的最近公共祖先
 */
public class LowerAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        // 在左子树中找p点和q点
        TreeNode findInLeft = lowestCommonAncestor(root.left, p, q);
        TreeNode findInRight = lowestCommonAncestor(root.right, p, q);

        if (findInLeft != null && findInRight != null) {
            return root;
        }

        return findInLeft == null ? findInRight : findInLeft;
    }

    
    // 使用hashmap记录下每个节点的父节点，以便向上遍历
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        findFather(root);
        hm.put(root, null);

        TreeNode cur = p;
        // 找到p对应的path
        HashSet<TreeNode> path = new HashSet<>();
        while (cur != null) {
            path.add(cur);
            cur = hm.get(cur);
        }

        cur = q;
        while (!path.contains(cur)) {
            cur = hm.get(cur);
        }

        return cur;
    }

    HashMap<TreeNode, TreeNode> hm = new HashMap<>();

    private void findFather(TreeNode cur) {
        if (cur == null) {
            return;
        }
        
        if (cur.left != null) {
            hm.put(cur.left, cur);
        }
        if (cur.right != null) {
            hm.put(cur.right, cur);
        }

        findFather(cur.left);
        findFather(cur.right);
    }
}
