/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-07 10:41:55
 * @LastEditTime: 2022-12-30 16:16:16
 */
package linkedlist.intersect_linkedlist;

import linkedlist.ListNode;

public class get_intersect_node {
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
