/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-03-31 21:47:50
 * @LastEditTime: 2023-03-31 21:56:12
 */
package tree.subtree;

import tree.TreeNode;

/**
 * leetcode 剑指offer26 middle 树的子结构
 * 
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 */
public class SubStructure {
    // Wrong way
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null && B == null) {
            return true;
        }

        if (A == null || B == null) {
            return false;
        }

        // 我最开始是这样写的，这样写其实是不对的，因为即使A当前节点的值等于B当前节点的值，也不一定非要两者都以当前节点为根
        // 比如A=[4,2,3,4,5,6,7,8,9]， B=[4,8,9]
        if (A.val == B.val) {
            return isSubStructure(A.left, B.right) && isSubStructure(A.right, B.right);
        } else {
            return isSubStructure(A.left, B) || isSubStructure(A.right, B);
        }
    }

    public boolean isSubStructure2(TreeNode A, TreeNode B) {
        if (A == null && B == null) {
            return true;
        }

        if (A == null || B == null) {
            return false;
        }

        boolean ans = false;

        if (A.val == B.val) {
            ans = helper(A, B);
        }

        if (ans) {
            return true;
        }

        return isSubStructure2(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean helper(TreeNode A, TreeNode B) {
        if (B == null) {
            // B遍历完全了
            return true;
        }

        if (A == null) {
            return false;
        }

        // 以A和B当前节点为根节点
        return A.val == B.val && helper(A.left, B.left) && helper(A.right, B.right);
    }
}
