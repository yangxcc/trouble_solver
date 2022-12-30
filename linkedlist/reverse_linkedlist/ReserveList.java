/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-05 19:33:29
 * @LastEditTime: 2022-12-30 17:45:23
 */
package linkedlist.reverse_linkedlist;

import linkedlist.ListNode;

public class ReserveList {
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
}
