package linkedlist.rotate_linkedlist;

import linkedlist.ListNode;

/**
 * leetcode 61 middle 旋转链表
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 */
public class RatateList {
    public ListNode rotateRight(ListNode head, int k) {
        ListNode cur = head;
        int count = 0;
        while (cur != null) {
            count++;
            cur = cur.next;
        }

        k %= count;
        if (k == 0) {
            // k是长度的整数倍
            return head;
        } else {
            ListNode fast = head;
            ListNode slow = head;
            while (k > 0) {
                fast = fast.next;
                k--;
            }

            ListNode pre = null;
            while (fast != null) {
                fast = fast.next;
                pre = slow;
                slow = slow.next;
            }

            pre.next = null;
            ListNode ans = slow;
            while (slow.next != null) {
                slow = slow.next;
            }
            slow.next = head;

            return ans;
        }
    }
}
