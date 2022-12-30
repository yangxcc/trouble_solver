/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-30 10:22:02
 * @LastEditTime: 2022-12-30 10:35:55
 */
package linkedlist.merge_linkedlist;

import linkedlist.ListNode;

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
}
