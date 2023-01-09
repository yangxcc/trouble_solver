/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-07 10:41:55
 * @LastEditTime: 2023-01-09 11:24:45
 */
package linkedlist.intersect_linkedlist;

import linkedlist.ListNode;

public class GetIntersectNode {
    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode helperA = headA, helperB = headB;
        while (helperA != helperB) {
            // if (helperA == null) {
            //     helperA = headB;
            // } else {
            //     helperA = helperA.next;
            // }

            // if (helperB == null) {
            //     helperB = headA;
            // } else {
            //     helperB = helperB.next;
            // }
            
            helperA = helperA == null ? headB : helperA.next;
            helperB = helperB == null ? headA : helperB.next;
        }
        return helperA;
    }
}
