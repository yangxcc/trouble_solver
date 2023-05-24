/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-24 21:32:51
 * @LastEditTime: 2023-05-24 21:56:14
 */
package tree.dyeing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import tree.TreeNode;

/**
 * leetcode LCP52 middle 二叉搜索树染色
 * 
 * 每位勇士面前设有一个二叉搜索树的模型，模型的根节点为 root，树上的各个节点值均不重复。
 * 初始时，所有节点均为蓝色。现在按顺序对这棵二叉树进行若干次操作， ops[i] = [type, x, y] 表示第 i 次操作为：
 * type 等于 0 时，将节点值范围在 [x, y] 的节点均染蓝
 * type 等于 1 时，将节点值范围在 [x, y] 的节点均染红
 * 
 * 请返回完成所有染色后，该二叉树中红色节点的数量。
 * 
 * 注意：
 * 题目保证对于每个操作的 x、y 值定出现在二叉搜索树节点中
 */
public class LCP52 {
    /**
     * 能够全部AC的思路：节点的颜色，只跟最后一次染色的操作有关系
     * 将所有未确定颜色的节点放入到一个哈希表中，从后往前遍历操作，将确定染色的节点从哈希表中移出去
     * 直到操作做完或者是哈希表为空
     * 
     * @param root
     * @param ops
     * @return
     */
    public int getNumber(TreeNode root, int[][] ops) {
        traverse(root);

        int ans = 0;
        for (int ele : hs) {
            for (int i = ops.length - 1; i >= 0; i--) {
                int type = ops[i][0], lower = ops[i][1], upper = ops[i][2];
                if (type == 0 && ele >= lower && ele <= upper) {
                    // 最后一次染成了蓝色
                    break;
                }

                if (type == 1 && ele >= lower && ele <= upper) {
                    ans++;
                    break;
                }
            }
        }

        return ans;
    }

    HashSet<Integer> hs = new HashSet<>();

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        hs.add(root.val);
        traverse(root.left);
        traverse(root.right);
    }

    /**
     * 我的笨死路，将所有节点的颜色放入到哈希表中，然后依次染色，遍历递归
     * 这种做法83/87，会超时，做一些其他多余的改动没用，通过用例数就是这些
     */

    public int getNumberWrong(TreeNode root, int[][] ops) {
        int j = 0;
        while (j < ops.length && ops[j][0] == 0) {
            j++;
        }
        // getNodeColor(root);
        for (int i = j; i < ops.length; i++) {
            if (root.val < ops[i][1]) {
                color(root.right, ops[i][0], ops[i][1], ops[i][2]);
            } else if (root.val > ops[i][2]) {
                color(root.left, ops[i][0], ops[i][1], ops[i][2]);
            } else {
                color(root, ops[i][0], ops[i][1], ops[i][2]);
            }
        }

        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : val2Color.entrySet()) {
            if (entry.getValue() == 1) {
                ans++;
            }
        }

        return ans;
    }

    // 需要知道当前节点的颜色
    HashMap<Integer, Integer> val2Color = new HashMap<>();
    // 不需要遍历，因为题目中给出了默认是白色，我们在下面染色的过程中再放入哈希表就行
    // private void getNodeColor(TreeNode root) {
    // if (root == null) {
    // return;
    // }

    // val2Color.put(root.val, 0);
    // getNodeColor(root.left);
    // getNodeColor(root.right);
    // }

    private void color(TreeNode cur, int type, int x, int y) {
        if (cur == null) {
            return;
        }

        if (cur.val < x) {
            color(cur.right, type, x, y);
        } else if (cur.val > y) {
            color(cur.left, type, x, y);
        } else {
            // cur肯定是要染色的
            if (type == 0) {
                // 染蓝色
                val2Color.put(cur.val, 0);
            } else if (type == 1) {
                // 染红色
                val2Color.put(cur.val, 1);
            }

            color(cur.left, type, x, y);
            color(cur.right, type, x, y);
        }
    }
}
