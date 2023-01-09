/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-05 19:33:29
 * @LastEditTime: 2023-01-09 11:12:22
 */
package linkedlist.reverse_linkedlist;

import linkedlist.ListNode;

public class ReserveList {
    // leetcode 206 simple 反转链表
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode node = reverseList(head.next);
        head.next.next = head;
        head.next = null;

        return node;
    }

    public ListNode reverseListLoop(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    /**
     * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
     * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
     * @param head
     * @param left
     * @param right
     * @return
     * 
     * leetcode 92 middle 反转链表2
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == 1) {
            return reverseNFromBegin(head, right);
        }

        head.next = reverseBetween(head.next, left-1, right-1);
        return head;
    }

    // 反转前n个节点，n从1开始
    ListNode reverseNext = null;
    public ListNode reverseNFromBegin(ListNode head, int n) {
        if (n <= 1) {
            reverseNext = head.next;
            return head;
        }
        if (head == null || head.next == null) {
            return head;
        }

        ListNode reverseN = reverseNFromBegin(head.next, n-1);
        head.next.next = head;
        // head.next = reverseN.next; // 不能这么写，因为在这里时已经翻转过来了，比如1-2-3-4-5-6,n=3,此时已经是321了，3后面不是4，而是2了
        head.next = reverseNext;

        return reverseN;
    }
}
