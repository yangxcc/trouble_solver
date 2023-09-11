/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-30 10:22:02
 * @LastEditTime: 2023-04-03 10:51:17
 */
package linkedlist.merge_linkedlist;

import linkedlist.ListNode;

/**
 * leetcode 21/剑指offer25 simple 合并两个排序的链表
 */
public class MergeTwo {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }


    private ListNode mergeTwoListsWithLoop(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (list1 != null || list2 != null) {
            if (list1 == null) {
                cur.next = list2;
                break;
            } else if (list2 == null) {
                cur.next = list1;
                break;
            } else if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    private ListNode mergeTwoListsWithLoop2(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (list1 != null || list2 != null) {
            int val1 = list1 == null ? Integer.MAX_VALUE : list1.val;
            int val2 = list2 == null ? Integer.MAX_VALUE : list2.val;
            if (val1 < val2) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}
