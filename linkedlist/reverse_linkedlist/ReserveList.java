/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-05 19:33:29
 * @LastEditTime: 2023-04-03 13:24:23
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
     * 
     * @param head
     * @param left
     * @param right
     * @return
     * 
     *         leetcode 92 middle 反转链表2
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == 1) {
            return reverseNFromBegin(head, right);
        }

        head.next = reverseBetween(head.next, left - 1, right - 1);
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

        ListNode reverseN = reverseNFromBegin(head.next, n - 1);
        head.next.next = head;
        // head.next = reverseN.next; //
        // 不能这么写，因为在这里时已经翻转过来了，比如1-2-3-4-5-6,n=3,此时已经是321了，3后面不是4，而是2了
        head.next = reverseNext;

        return reverseN;
    }

    // 反转双向链表
    public ListNode reverseDoubleLinkedList(ListNode head) {
        ListNode pre = null; // 前面的
        ListNode nxt = null; // 后面的

        ListNode cur = head;
        while (cur != null) {
            nxt = cur.next;
            cur.next = pre;
            cur.pre = nxt;

            pre = cur;
            cur = nxt;
        }

        return pre;
    }

    /**
     * leetcode 25 hard K个一组反转链表
     * 
     * k 是一个正整数，它的值小于或等于链表的长度。
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head;
        int count = 0;
        while (cur != null && count < k) {
            cur = cur.next;
            count++;
        }

        cur = head;
        ListNode pre = null, next = null;
        if (count == k) {
            while (count > 0) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;

                count--;
            }

            head.next = reverseKGroup(cur, k);
            return pre;
        } else {
            // count < k
            return head;
        }
    }

    /**
     * leetcode 24 middle 两两交换链表中的节点
     * 
     * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
     * 你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
     * 
     * 1234  2143
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode ans = head.next;
        ListNode reverse = swapPairs(ans.next);
        ans.next = head;
        head.next = reverse;

        return ans;
    }
}
