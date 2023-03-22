/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-04 08:59:07
 * @LastEditTime: 2023-03-22 15:24:14
 */
package linkedlist.delete_element;

import linkedlist.ListNode;

/**
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 */
public class RemoveLinkedlistElement {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        while (head != null) {
            while (head != null && head.val == val) {
                head = head.next;
            }
            pre.next = head;
            if (head != null) {
                head = head.next;
            }
            pre = pre.next;
        }

        return dummy.next;
    }


    /**
     * leetcode 83 simple 删除链表中的重复元素1
     * 给定一个已排序的链表的头 head， 删除所有重复的元素，
     * 使每个元素只出现一次 。返回 已排序的链表 。
     * 
     * 比如1 -> 1 -> 2，删除之后变成了1 -> 2
     * 
     */
    public ListNode deleteDuplicates1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode nxt = head.next;

        while (nxt != null && head.val == nxt.val) {
            nxt = nxt.next;
        }

        head.next = deleteDuplicates1(nxt);

        return head;
    }

    public ListNode deleteDuplicatesWithLoop(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head;
        ListNode nxt = head.next;

        while (cur != null && nxt != null) {
            if (cur.val == nxt.val) {
                while (nxt != null && cur.val == nxt.val) {
                    nxt = nxt.next;
                }
                cur.next = nxt;
                cur = cur.next;
            } else {
                cur = nxt;
                nxt = nxt.next;
            }
        }

        return head;
    }

    /**
     * leetcode 82 middle 删除链表中的重复元素2
     * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，
     * 只留下不同的数字 。返回 已排序的链表 。
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode pre = dummy;
        ListNode cur = head;

        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                pre.next = cur.next;
                cur = cur.next;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }

        return dummy.next;
    }   
}
