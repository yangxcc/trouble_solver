package linkedlist.copy_linkedlist;

import linkedlist.ListNode;

/**
 * leetcode 剑指offer35 复杂链表的复制
 * 
 * 请实现 copyRandomList 函数，复制一个复杂链表。
 * 在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
 */
public class CopyRandomList {
    public ListNode copy(ListNode head) {
        if (head == null) {
            return null;
        }

        // 在每一个节点的后面加上一个复制后的节点
        ListNode cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            ListNode copyNode = new ListNode(cur.val);
            copyNode.next = nxt;
            cur.next = copyNode;
            cur = nxt;
        }

        // 去处理random节点
        cur = head;
        while (cur != null) {
            ListNode copyNode = cur.next;
            copyNode.random = cur.random == null ? null : cur.random.next;
            cur = copyNode.next;
        }


        // 断开链
        ListNode ans = head.next;
        ListNode copyList = head.next;
        cur = head;
        while (cur != null) {
            cur.next = copyList.next;
            cur = cur.next;
            if (cur == null) {
                return ans;
            }
            copyList.next = cur.next;
            copyList = copyList.next;
        }
        return ans;
    }
}
