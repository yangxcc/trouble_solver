/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-11-02 14:31:27
 * @LastEditTime: 2023-02-16 19:51:26
 */
package tree.bst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tree.TreeNode;

/**
 * leetcode 501 simple 二叉搜索树中的众数
 * 
 * 假定 BST 满足如下定义：
 * 结点左子树中所含节点的值 小于等于 当前节点的值
 * 结点右子树中所含节点的值 大于等于 当前节点的值
 * 左子树和右子树都是二叉搜索树
 */
public class Mode {
    TreeNode pre = null;
    int maxCount = 0;
    int curCount = 0;
    List<Integer> ans;

    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[] {};
        }
        inOrder(root);

        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }

        return res;
    }

    public void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);

        if (pre != null && pre.val == node.val) {
            curCount++;
        } else {
            curCount = 1;
        }
        pre = node;

        if (curCount > maxCount) {
            ans =  new ArrayList<>(); 
            ans.add(node.val);
            maxCount = curCount;
        } else if(curCount == maxCount){
            ans.add(node.val);
        }

        inOrder(node.right);
    }
}
