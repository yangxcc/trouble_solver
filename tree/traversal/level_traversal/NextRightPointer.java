/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-23 20:00:35
 * @LastEditTime: 2023-01-19 15:19:05
 */
package tree.traversal.level_traversal;

import java.util.ArrayDeque;
import java.util.Deque;


/**
 * leetcode 116 middle 填充每个节点的下一个右侧节点指针, 给定的二叉树是完美二叉树
 * 完美二叉树：所有叶子节点都在同一层，每个父节点都有两个子节点
 * 
 * leetcode 117 middle 填充每个节点的下一个右侧节点指针II，给定的二叉树是普通的二叉树
 * 
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
 * 如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 */
public class NextRightPointer {
    public Node connect4Leetcode116(Node root) {
        process4Leetcode116(root);
        return root;
    }

    public void process4Leetcode116(Node root) {
        if (root == null) {
            return;
        }

        // 碰到叶子节点就返回
        if (root.left != null) {
            root.left.next = root.right;
            if (root.next != null) {
                root.right.next = root.next.left;
            }
        }

        process4Leetcode116(root.left);
        process4Leetcode116(root.right);
    }

    public Node connect4Leetcode117(Node root) {
        process4Leetcode117(root);
        return root;
    }

    public void process4Leetcode117(Node root) {
        if (root == null) {
            return;
        }
        
        if (root.left == null && root.right == null) {
            return;
        }

        if (root.left != null && root.right != null) {
            root.left.next = root.right;
        }

        if (root.left != null && root.right == null) {
            root.left.next = getNextNode(root.next);
        }

        if (root.right != null) {
            root.right.next = getNextNode(root.next);
        }

        process4Leetcode117(root.right);
        process4Leetcode117(root.left);
    }

    private Node getNextNode(Node node) {
        if (node == null) {
            return null;
        }

        if (node.left != null) {
            return node.left;
        }

        if (node.left == null && node.right != null) {
            return node.right;
        }        

        return getNextNode(node.next);
    }

    // 使用层序遍历能够解决116和117，感觉也挺优雅😂
    public Node useLevelTraversal(Node root) {
        if (root == null) {
            return null;
        }
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            Node[] levelNodes = new Node[size];
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                levelNodes[i] = node;
            }

            for (int i = 0; i < levelNodes.length-1; i++) {
                levelNodes[i].next = levelNodes[i+1];
            }
        }

        return root;
    }
}

class Node {
    int val;
    Node left;
    Node right;
    Node next;

    public Node(int value) {
        this.val = value;
        this.left = null;
        this.right = null;
        this.next = null;
    }
}
