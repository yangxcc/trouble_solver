package tree.ancestor;

import tree.TreeNode;

/**
 * leetcode 865 middle 具有所有最深节点的最小子树
 * 从这道题目的描述中，我们能够提取出两点信息：
 * 一是我们需要找到所有的最深节点
 * 然后找到的是所有最深节点（>=1）的最近公共祖先
 */
public class SmallestSubtreeWithAllDeepestNodes {
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return process(root).node;
    }

    private Pair process(TreeNode root) {
        if (root == null) {
            return new Pair(null, 0);
        }

        Pair leftPart = process(root.left);
        Pair rightPart = process(root.right);
        if (leftPart.depth == rightPart.depth) {
            // 两边的最大深度相同，说明当前节点能够作为最深节点的最近公共祖先
            return new Pair(root, leftPart.depth + 1);
        }

        // 如果两边不相等，那么最近公共祖先肯定是在高度较高的那一部分
        Pair ans = leftPart.depth > rightPart.depth ? leftPart : rightPart;
        ans.depth++;
        return ans;
    }

    class Pair {
        // 表示的是以当前节点作为最近公共祖先
        TreeNode node;
        // 以当前节点为根，二叉树的最大深度
        int depth;

        public Pair(TreeNode _node, int _depth) {
            this.node = _node;
            this.depth = _depth;
        }
    }
}
