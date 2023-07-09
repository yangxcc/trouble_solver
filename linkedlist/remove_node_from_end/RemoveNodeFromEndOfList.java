/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-07 10:07:22
 * @LastEditTime: 2023-07-09 21:19:54
 */
package linkedlist.remove_node_from_end;

import linkedlist.ListNode;

/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 */
public class RemoveNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 注意要有dummy，这样的话删除第一个节点时，就不需要特殊逻辑了
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;
        while (n > 0) {
            fast = fast.next;
            n--;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }   
        slow.next = slow.next.next;
        return dummy.next;
    }
}