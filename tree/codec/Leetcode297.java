/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-27 09:53:59
 * @LastEditTime: 2023-01-31 12:09:45
 */
package tree.codec;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

import sun.reflect.generics.tree.Tree;
import tree.TreeNode;

/**
 * leetcode 297 hard 二叉树的序列化与反序列化
 */
public class Leetcode297 {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString();
    }

    public void preOrder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#,");
            return;
        }

        sb.append(root.val).append(",");
        preOrder(root.left, sb);
        preOrder(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("#,")) {
            return null;
        }

        String[] elements = data.split(",");
        Deque<String> q = new ArrayDeque<>();
        for (String ele : elements) {
            q.add(ele);
        }

        return deserializeHelper(q);
    }

    public TreeNode deserializeHelper(Deque<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        String nodeVal = queue.poll();
        if (nodeVal.equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(nodeVal));
        root.left = deserializeHelper(queue);
        root.right = deserializeHelper(queue);

        return root;
    }


    // 二叉搜索树的编码解码
    // Encodes a tree to a single string.
    public String serializeBST(TreeNode root) {
        // 和普通树的序列化方式一样，可以使用前、中、后序遍历的方式，也能够使用层序遍历
        // 只是不需要将空节点再用#代替了
        return "";
    }

    // Decodes your encoded data to tree.
    public TreeNode deserializeBST(String data) {
        String[] elements = data.split(",");
        LinkedList<Integer> q = Arrays.stream(elements).map(Integer::parseInt).collect(Collectors.toCollection(LinkedList::new));
        return deserializeBSTHelper(q, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // 在[min, max]区间内，通过list中的元素反序列化出一刻BST
    private TreeNode deserializeBSTHelper(LinkedList<Integer> list, int min, int max) {
        if (list.isEmpty()) {
            return null;
        }
        // 因为是使用前序遍历序列化的，所以第一个元素是根节点
        int rootVal = list.getFirst();
        if (rootVal < min || rootVal > max) {
            return null;
        }
        list.removeFirst(); // 必须在这里删除，不能再上面getFirst的时候删除

        TreeNode root = new TreeNode(rootVal);
        root.left = deserializeBSTHelper(list, min, rootVal);
        root.right = deserializeBSTHelper(list, rootVal, max);
        return root;
    }
}
