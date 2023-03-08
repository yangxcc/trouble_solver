/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-05 19:58:43
 * @LastEditTime: 2023-03-01 14:59:16
 */
package linkedlist.swap_node;

import linkedlist.ListNode;

/**
 * 两两交换链表中的节点
 */
public class SwapNodeInPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode nxt = head.next;  // 目的就是为了返回
        ListNode reverse = swapPairs(nxt.next);
        head.next = reverse;
        nxt.next = head;

        return nxt;
    }

    public static ListNode swapPairsLoop(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 将链表按照奇数位和偶数位拆分
        ListNode odd = head, even = head.next, evenHelper = head.next;
        while (evenHelper != null && evenHelper.next != null) {
            odd.next = evenHelper.next;
            odd = odd.next;
            evenHelper.next = odd.next;
            evenHelper = evenHelper.next;
        }

        odd.next = null;
        printList(head);
        printList(even);
        return merge(head, even);
    }

    public static ListNode merge(ListNode l1, ListNode l2) {
        ListNode ans = l2;
        while (l1 != null && l2 != null) {
            ListNode l2Next = l2.next;
            ListNode l1Next = l1.next;
            l2.next = l1;
            l2 = l2Next;
            if (l2 == null) {
                break;
            }
            l1.next = l2;
            l1 = l1Next;
        }
        return ans;
    }

    public static void printList(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.printf("%d -> ", cur.val);
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        swapPairsLoop(node1);
    }
}
