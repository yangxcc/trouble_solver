package linkedlist.reorder_linkedlist;

import linkedlist.ListNode;

/**
 * leetcode 143 middle 重排链表
 * 
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * L0 → L1 → … → Ln - 1 → Ln
 * 请将其重新排列后变为：
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */
public class ReorderLinkedlist {
    public void reorderList(ListNode head) {
        // 思路：快慢指针，找到链表的中点，然后反转后半部分，然后合并前半部分和后半部分
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 记录下后半部分
        ListNode after = slow.next;
        // 把链断开
        slow.next = null;
        ListNode helper = reverse(after);

        merge(head, helper);
    }

    private ListNode reverse(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode reverse = reverse(node.next);
        node.next.next = node;
        node.next = null;

        return reverse;
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode tmp = l1;
        while (l2 != null) {
            ListNode nxt1 = tmp.next;
            ListNode nxt2 = l2.next;
            tmp.next = l2;
            l2.next = nxt1;
            l2 = nxt2;
            tmp = nxt1;
        }

        return l1;
    }
}
