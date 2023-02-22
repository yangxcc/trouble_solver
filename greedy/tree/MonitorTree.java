package greedy.tree;

import tree.TreeNode;

/**
 * leetcode 968 监控二叉树 hard
 * 
 * 给定一个二叉树，我们在树的节点上安装摄像头。
 * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
 * 计算监控树的所有节点所需的最小摄像头数量。
 */
public class MonitorTree{
    int ans = 0;
    public int minCameraCover(TreeNode root) {
        if (checkNodeStatus(root) == 0) {
            ans++;
        }

        return ans;
    }

    // 怎么让需要的摄像头尽可能的少，答案是不要试图给叶子节点上安装摄像头
    // 也就是从下往上遍历，这恰好是后序遍历能做的事
    // 定节点的三个状态：未被覆盖（0），有摄像头（1），已经被覆盖了（2）
    public int checkNodeStatus(TreeNode root) {
        if (root == null) {
            return 2;
        }

        int leftStatus = checkNodeStatus(root.left);
        int rightStatus = checkNodeStatus(root.right);

        if (leftStatus == 0 || rightStatus == 0) {
            ans++; 
            return 1;
        }

        if (leftStatus == 1 || rightStatus == 1) {
            return 2;
        }

        if (leftStatus == 2 && rightStatus == 2) {
            return 0;
        }

        return -1;
    }
}