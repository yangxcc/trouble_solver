package tree.subtree;

/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-31 12:18:39
 * @LastEditTime: 2023-01-31 12:42:09
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import tree.TreeNode;

/**
 * leetcode 652 middle 寻找重复的子树
 * 
 * 给你一棵二叉树的根节点 root ，返回所有 重复的子树。
 * 对于同一类的重复子树，你只需要返回其中任意 一棵 的根结点即可。
 * 如果两棵树具有 相同的结构 和 相同的结点值 ，则认为二者是 重复 的。
 */
public class Leetcode652 {
    // 对于任意一个节点，我们需要知道的是 当前节点的形状以及其他节点的形状
    // 想要知道自己的形状就需要进行序列化
    // 如果使用HashSet来判断是否存在重复，那么对于多次重复的无法处理，
    // 所以使用HashMap，加上一个频率值来判断重复
    HashMap<String, Integer> memo = new HashMap<>();
    List<TreeNode> res = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse(root);
        return res;
    }

    public String traverse(TreeNode root) {
        if (root == null) {
            return "#";
        }

        // 左右子树的序列化结果
        String left = traverse(root.left);
        String right = traverse(root.right);
        // 使用后续遍历进行序列化
        String subTree = left + "," + right + "," + root.val;

        if (memo.containsKey(subTree)) {
            if (memo.get(subTree).equals(1)) {
                res.add(root);
            }
            memo.put(subTree, memo.get(subTree) + 1);

        } else {
            memo.put(subTree, 1);
        }

        return subTree;
    }
}
