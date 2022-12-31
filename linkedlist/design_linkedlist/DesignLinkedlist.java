/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-04 16:45:15
 * @LastEditTime: 2022-12-31 21:22:09
 */
package linkedlist.design_linkedlist;

/**
 * leetcode 707 middle. 设计链表
 * 设计链表的实现。您可以选择使用单链表或双链表。
 * 单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。
 * 如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
 */
public class DesignLinkedlist {

    ListNode dummy;
    public DesignLinkedlist() {
        dummy = new ListNode(-1);
        dummy.next = dummy;
        dummy.prev = dummy;
    }
    
    // index从0开始，是双向链表，到了最后还会转回来
    public int get(int index) {
        ListNode cur = dummy.next;
        while (index > 0 && cur != dummy) {
            cur = cur.next;
            index--;
        }

        return index == 0 ? cur.val : -1;
    }
    
    public void addAtHead(int val) {
        ListNode node = new ListNode(val);
        node.next = dummy.next;
        dummy.next.prev = node;
        node.prev = dummy;
        dummy.next = node;
    }
    
    public void addAtTail(int val) {
        ListNode node = new ListNode(val);
        node.prev = dummy.prev;
        dummy.prev.next = node;
        node.next = dummy;
        dummy.prev = node;
    }
    
    /*
     * 在链表中的第 index 个节点之前添加值为 val  的节点。
     * 如果 index 等于链表的长度，则该节点将附加到链表的末尾。
     * 如果 index 大于链表长度，则不会插入节点。
     * 如果 index 小于0，则在头部插入节点。
     * 
     */
    public void addAtIndex(int index, int val) {
        ListNode node = new ListNode(val);
        ListNode cur = dummy.next; 

        while (index > 0 && cur != dummy) {
            cur = cur.next;
            index--;
        }

        // 正常的话，出来就已经找到第index位置的节点了
        if (index > 0) {
            return;
        }

        node.prev = cur.prev;
        cur.prev.next = node;
        node.next = cur;
        cur.prev = node;
    }
    
    public void deleteAtIndex(int index) {
        ListNode cur = dummy.next; 

        while (index > 0 && cur != dummy) {
            cur = cur.next;
            index--;
        }

        // 正常的话，出来就已经找到第index位置的节点了
        if (index > 0) {
            return;
        }

        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
    }
}

class ListNode {
    int val; 
    ListNode next;
    ListNode prev; 

    public ListNode(int val) {
        this.val = val;
        this.next = null;
        this.prev = null;
    }
}