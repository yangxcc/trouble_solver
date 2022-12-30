/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-30 10:36:32
 * @LastEditTime: 2022-12-30 11:04:01
 */
package linkedlist.merge_linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;

import linkedlist.ListNode;

public class MergeK {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> q = new PriorityQueue<ListNode>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
            
        });
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        for (ListNode head : lists) {
            q.add(head);
        }

        while (!q.isEmpty()) {
            ListNode node = q.poll();
            cur.next = node;
            if (node.next != null) {
                q.add(node.next);
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}
