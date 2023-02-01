package tree.bst;

import tree.TreeNode;

/**
 * leetcode 230 middle 二叉搜索树中第k小的元素
 * 
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 */
public class minK {
    int count = 0;
    int res = -1;
    public int kthSmallest(TreeNode root, int k) {
        if (root == null || k < -1) {
            return -1;
        }

        inOrder(root, k);
        return res;
    }

    public void inOrder(TreeNode root, int k) {
        if (root == null || k < -1) {
            return;
        }

        inOrder(root.left, k);
        count++;
        if (count == k) {
            res = root.val;
            return;
        }
        inOrder(root.right, k);
    }
}

/**
 * 本题还有一个进阶问法
 * 
 * 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
 * 
 * 事实上，对于二叉搜索树，有一个很常见的优化方法，那就是给树节点中加入size属性，表示的是以当前节点为根的子树的节点总数
 * 
 * 这样的话，我们就能够知道树中任何一个节点在整棵树中的排名，因为我们能够知道左子树有多少节点，右子树有多少节点
 * 
 */