package tree.bst;

import tree.TreeNode;

/**
 * leetcode 1373 hard 二叉搜索子树的最大键值和
 * 
 * 给你一棵以 root 为根的 二叉树 ，请你返回 任意 二叉搜索子树的最大键值和。
 * (即找到和最大的二叉搜索子树)
 */
public class MaxSum {
    int ans = 0;

    public int maxSumBST(TreeNode root) {
        process(root, null, null);
        return ans;
    }

    /**
     * 对于任何一个节点 我们需要做的事情有
     * 判断以该节点为根的子树 是否为 BST
     * 若是，则需要得到该子树的和
     */
    private Flag process(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return new Flag(true, 0);
        }

        if (min != null && root.val <= min.val) {
            return new Flag(false, 0);
        }

        if (max != null && root.val >= max.val) {
            return new Flag(false, 0);
        }

        Flag leftProcess = process(root.left, min, root);
        Flag rightProcess = process(root.right, root, max);

        if (leftProcess.isBst && rightProcess.isBst) {
            ans = Math.max(ans, leftProcess.sum + rightProcess.sum + root.val);
            return new Flag(true, leftProcess.sum + rightProcess.sum + root.val);
        }

        return new Flag(false, 0);
    }
}

class Flag {
    boolean isBst;
    int sum;

    public Flag(boolean isBst, int sum) {
        this.isBst = isBst;
        this.sum = sum;
    }
}


/**
 * ************* 上面的代码 叶子节点 的测试用例过不去，不清楚为啥 ***************
 */

class Solution {
    int ans = 0;

    public int maxSumBST(TreeNode root) {
        process(root);
        return ans;
    }

    /**
     * 
     * @param root
     * @return
     * 返回的数组中包含四个元素，从左至右分别表示
     * ans[0] --- 以root为根的树是否为bst, 1表示yes，0表示no
     * ans[1] --- 以root为根的树的最小值
     * ans[2] --- 以root为根的树的最大值
     * ans[3] --- 以root为根的树的和
     */
    private int[] process(TreeNode root) {
        if (root == null) {
            return new int[]{1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }

        int[] leftProcess = process(root.left);
        int[] rightProcess = process(root.right);

        int[] res = new int[4];
        if (leftProcess[0] == 1 && rightProcess[0] == 1 && root.val > leftProcess[2] && root.val < rightProcess[1]) {
            res[0] = 1;
            res[1] = Math.min(leftProcess[1], root.val);
            res[2] = Math.max(rightProcess[2], root.val);
            res[3] = leftProcess[3] + rightProcess[3] + root.val;
            ans = Math.max(ans, leftProcess[3] + rightProcess[3] + root.val);
        } else {
            res[0] = 0;
        }

        return res;
    }
} 