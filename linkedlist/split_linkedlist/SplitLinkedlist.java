/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-30 11:33:23
 * @LastEditTime: 2022-12-30 15:42:40
 */
package linkedlist.split_linkedlist;

import linkedlist.ListNode;

public class SplitLinkedlist {
    public ListNode partition(ListNode head, int x) {
        ListNode dummy1 = new ListNode(-1); // 用于连接小的部分
        ListNode dummy2 = new ListNode(-1); // 用于连接大于等于的部分
        ListNode p1 = dummy1, p2 = dummy2;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                p1.next = cur;
                p1 = p1.next;
            } else {
                p2.next = cur;
                p2 = p2.next;
            }
            // 断开原链表中的每个节点的 next 指针
            ListNode tmp = cur.next;
            cur.next = null;
            cur = tmp;
        }
        p1.next = dummy2.next;
        return dummy1.next;
    }
}
