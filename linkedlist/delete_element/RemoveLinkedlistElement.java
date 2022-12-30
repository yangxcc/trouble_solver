/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-04 08:59:07
 * @LastEditTime: 2022-12-30 17:18:16
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
}
