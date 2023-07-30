package linkedlist;

import javax.management.MBeanRegistration;

/**
 * leetcode 148 middle 对链表进行排序
 *
 */
public class SortedLinkedList {
    /**
     * 使用归并排序对链表进行排序
     * @param head
     * @return
     */
    public ListNode sortListWithMergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 1. 快慢指针找到链表中点，将链表分成两个部分
        // 2. 对前一部分进行mergeSort，对后一部分进行mergeSort
        // 3. 对两部分进行merge
        ListNode fast = head, slow = head;
        ListNode pre = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;
        }

        pre.next = null; // 断开链
        ListNode beforeHalf = sortListWithMergeSort(head);
        ListNode afterHalf = sortListWithMergeSort(slow);

        return merge(beforeHalf, afterHalf);
    }

    // 两个有序链表的合并
    private ListNode merge(ListNode p1, ListNode p2) {
        if (p1 == null) {
            return p2;
        }

        if (p2 == null) {
            return p1;
        }

        if (p1.val < p2.val) {
            p1.next = merge(p1.next, p2);
            return p1;
        } else {
            p2.next = merge(p2.next, p1);
            return p2;
        }
    }


    public ListNode sortListWithQuickSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 划分为三个区域，数组中的partition过程
        // h1,t1是比pivot小的部分
        // h2,t2是等于pivot的部分
        // h3,t3是比pivot大的部分
        ListNode h1 = new ListNode(0); // dummy节点，所以下面都得next
        ListNode t1 = h1;
        ListNode h2 = new ListNode(0);
        ListNode t2 = h2;
        ListNode h3 = new ListNode(0);
        ListNode t3 = h3;

        ListNode cur = head;
        int pivot = head.val;

        while (cur != null) {
            ListNode next = cur.next;
            if (cur.val < pivot) {
                cur.next = null;
                t1.next = cur;
                t1 = t1.next;
            } else if (cur.val == pivot) {
                cur.next = null;
                t2.next = cur;
                t2 = t2.next;
            } else {
                cur.next = null;
                t3.next = cur;
                t3 = t3.next;
            }
            cur = next;
        }

        h1 = sortListWithQuickSort(h1.next);
        h3 = sortListWithQuickSort(h3.next);

        // h1有可能是空的，h2一定不是空的
        h2 = h2.next;
        t2.next = h3;

        if (h1 == null) {
            return h2;
        } else {
            // 还需要将h1和h2连接起来
            t1 = h1;
            while (t1.next != null) {
                t1 = t1.next;
            }
            t1.next = h2;
            return h1;
        }
    }
}
