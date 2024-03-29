/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-19 13:52:07
 * @LastEditTime: 2023-01-26 09:54:21
 */
package tree;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    TreeNode() {}
    public TreeNode(int val) { this.val = val; }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}