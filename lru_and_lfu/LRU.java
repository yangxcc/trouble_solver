/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-03-14 14:19:24
 * @LastEditTime: 2023-03-14 15:30:35
 */
package lru_and_lfu;

import java.util.HashMap;

public class LRU {
    private int capacity;
    private HashMap<Integer, Node> cache;
    private DoubleLinkedList dlList;

    public LRU(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        dlList = new DoubleLinkedList();
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }

        makeKeyFirst(key);
        return cache.get(key).val;
    }

    private void makeKeyFirst(int key) {
        // 将节点从链表的原位置删除掉，然后把这个节点放到尾部
        Node node = cache.get(key);
        dlList.remove(node);
        dlList.addNode(node);
    }

    public void put(int key, int val) {
        if (cache.containsKey(key)) {
            makeKeyFirst(key);
            cache.get(key).val = val;
        } else {
            if (cache.size() == capacity) {
                Node firstKey = dlList.removeFirst();
                // 需要知道first的key
                cache.remove(firstKey.key);
            }
            Node node = new Node(key, val);
            cache.put(key, node);
            dlList.addNode(node);
        }
    }
}

class Node {
    int key;
    int val;
    Node pre;
    Node next;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }

    public Node() {
    }
}

class DoubleLinkedList {
    Node dummyHead;
    Node dummyTail;
    int size;

    public DoubleLinkedList() {
        dummyHead = new Node();
        dummyTail = new Node();
        dummyHead.next = dummyTail;
        dummyTail.pre = dummyHead;

        size = 0;
    }

    // 采用尾插法，将新元素放到末尾
    public void addNode(Node node) {
        node.pre = dummyTail.pre;
        dummyTail.pre.next = node;
        node.next = dummyTail;
        dummyTail.pre = node;
        size++;
    }

    // 删除一个节点（这里就体现出了dummy节点的作用了，不需要额外进行判断空指针的处理）
    public void remove(Node node) {
        if (node == dummyHead || node == dummyTail) {
            return;
        }
        node.pre.next = node.next;
        node.next.pre = node.pre;
        node.pre = null;
        node.next = null;
        size--;
    }

    public Node removeFirst() {
        if (dummyHead.next == dummyTail) {
            return null;
        }
        Node first = dummyHead.next;
        remove(first);
        return first;
    }
}