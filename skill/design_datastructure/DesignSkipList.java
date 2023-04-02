package skill.design_datastructure;

import java.util.Random;

/**
 * leetcode 1206 设计跳表 hard
 * 不使用任何库函数，设计一个 跳表 。
 */
public class DesignSkipList {
    private int level = 10;

    class Node {
        int val;
        // 用来保存该节点每一层的下一个节点
        Node[] ne = new Node[level];

        public Node(int _val) {
            this.val = _val;
        }
    }

    Random random = new Random();
    Node head = new Node(-1);

    /**
     * 查找出每一层比 target 严格小的最后一个节点，将其存成 ns 数组。
     * 即 ns[i] 为 level=i 层严格比 t 小的最后一个节点。
     */
    private void find(int target, Node[] ns) {
        Node cur = head;
        for (int i = level - 1; i >= 0; i--) {
            while (cur.ne[i] != null && cur.ne[i].val < target) {
                cur = cur.ne[i]; // 在同一层往前走一步
            }

            ns[i] = cur;
        }
    }

    public DesignSkipList() {
    }

    public void add(int num) {
        Node[] ns = new Node[level];
        find(num, ns);
        Node target = new Node(num);
        for (int i = 0; i < level; i++) {
            target.ne[i] = ns[i].ne[i];
            ns[i].ne[i] = target;

            if (random.nextInt(2) == 0) {
                break;
            }
        }
    }

    public boolean erase(int num) {
        Node[] ns = new Node[level];
        find(num, ns);

        if (ns[0].ne[0] == null || ns[0].ne[0].val != num) {
            return false;
        }

        for (int i = 0; i < level; i++) {
            ns[i].ne[i] = ns[i].ne[i] == null ? null : ns[i].ne[i].ne[i];
        }
        return true;
    }

    public boolean search(int target) {
        Node[] ns = new Node[level];
        find(target, ns);
        // ns[0]表示的是在第0层，比target严格小的最后一个节点
        return ns[0].ne[0] != null && ns[0].ne[0].val == target;
    }
}
