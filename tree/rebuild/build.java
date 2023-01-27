/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-11-01 21:30:13
 * @LastEditTime: 2023-01-27 09:32:18
 */
package tree.rebuild;

import java.util.HashMap;

import tree.TreeNode;

/**
 * 以下的三道题目中都有提示：数组中都没有重复元素
 */
public class Build {
    /**
     * leetcode 105 middle 从前序与中序遍历序列构造二叉树
     * 前序：根 左 右
     * 中序：左 根 右
     * 
     * @param preorder
     * @param inorder
     * @return
     */

    HashMap<Integer, Integer> hm4PreIn;

    public TreeNode buildTreeFromPreIn(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length) {
            return null;
        }
        // 目的是 在inorder中找到root的下标
        hm4PreIn = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            hm4PreIn.put(inorder[i], i);
        }

        return build4PreIn(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    public TreeNode build4PreIn(int[] preorder, int[] inorder, int preorderLeftIdx, int preorderRightIdx,
            int inorderLeftIdx, int inorderRightIdx) {
        if (preorderLeftIdx > preorderRightIdx) {
            return null;
        }

        int rootVal = preorder[preorderLeftIdx];
        int rootIdx = hm4PreIn.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        int leftTreeSize = rootIdx - inorderLeftIdx;

        root.left = build4PreIn(preorder, inorder, preorderLeftIdx + 1, preorderLeftIdx + leftTreeSize, inorderLeftIdx,
                rootIdx - 1);
        root.right = build4PreIn(preorder, inorder, preorderLeftIdx + leftTreeSize + 1, preorderRightIdx, rootIdx + 1,
                inorderRightIdx);

        return root;
    }

    /**
     * leetcode 106 middle 从后序与中序遍历序列构造二叉树
     * 后序：左 右 根
     * 中序：左 根 右
     * 
     * @param preorder
     * @param inorder
     * @return
     */

    HashMap<Integer, Integer> hm4InPost;

    public TreeNode buildTreeFromInPost(int[] inorder, int[] postorder) {
        if (inorder.length != postorder.length) {
            return null;
        }

        hm4InPost = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            hm4InPost.put(inorder[i], i);
        }

        return build4InPost(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    public TreeNode build4InPost(int[] inorder, int[] postorder, int inorderLeftIdx, int inorderRightIdx,
            int postorderLeftIdx, int postorderRightIdx) {

        // 太傻了，没写递归出口😔
        if (postorderLeftIdx > postorderRightIdx) {
            return null;
        }

        int rootVal = postorder[postorderRightIdx];
        TreeNode root = new TreeNode(rootVal);
        int rootIdx = hm4InPost.get(rootVal);
        int leftTreeSize = rootIdx - inorderLeftIdx;

        root.left = build4InPost(inorder, postorder, inorderLeftIdx, rootIdx - 1, postorderLeftIdx,
                postorderLeftIdx + leftTreeSize - 1);
        root.right = build4InPost(inorder, postorder, rootIdx + 1, inorderRightIdx, postorderLeftIdx + leftTreeSize,
                postorderRightIdx - 1);
        return root;
    }

    /**
     * leetcode 889 middle 根据前序和后序遍历构造二叉树
     * 前序：根 左 右
     * 后序：左 右 根
     * 
     * @param preorder
     * @param postorder
     * @return
     */
    HashMap<Integer, Integer> hm4PrePost;

    public TreeNode buildTreeFromPrePost(int[] preorder, int[] postorder) {
        if (preorder.length != postorder.length) {
            return null;
        }
        
        hm4PrePost = new HashMap<>();
        for (int i = 0; i < preorder.length; i++) {
            hm4PrePost.put(postorder[i], i);
        }

        return build4PrePost(preorder, postorder, 0, preorder.length - 1, 0, postorder.length - 1);
    }

    public TreeNode build4PrePost(int[] preorder, int[] postorder, int preorderLeftIdx, int preorderRightIdx,
            int postorderLeftIdx, int postorderRightIdx) {

        if (preorderLeftIdx > preorderRightIdx) {
            return null;
        }

        int rootVal = preorder[preorderLeftIdx];
        TreeNode root = new TreeNode(rootVal);
        int leftTreeRootVal = preorder[preorderLeftIdx + 1];
        int leftTreeRootIdx = hm4PrePost.get(leftTreeRootVal);
        int leftTreeSize = leftTreeRootIdx - postorderLeftIdx;

        root.left = build4PrePost(preorder, postorder, preorderLeftIdx + 1, preorderLeftIdx + leftTreeSize + 1,
                postorderLeftIdx, postorderLeftIdx + leftTreeSize);

        root.right = build4PrePost(preorder, postorder, preorderLeftIdx + leftTreeSize + 2, preorderRightIdx,
                postorderLeftIdx + leftTreeSize + 1, postorderRightIdx - 1);

        return root;
    }
}
