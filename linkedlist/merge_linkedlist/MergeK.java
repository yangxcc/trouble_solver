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


    // 使用递归方式mergeK
    public ListNode mergeKLists2(ListNode[] lists) {
        return mergeKLists2Helper(lists, 0, lists.length - 1);
    }

    private ListNode mergeKLists2Helper(ListNode[] listNodes, int left, int right) {
        if (left > right) {
            return null;
        } else if (left == right) {
            return listNodes[left];
        }

        int mid = left + (right - left) / 2;
        ListNode oneHalf = mergeKLists2Helper(listNodes, left, mid);
        ListNode anotherHalf = mergeKLists2Helper(listNodes, mid + 1, right);
        return mergeTwo(oneHalf, anotherHalf);
    }

    private ListNode mergeTwo(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        if (list1.val < list2.val) {
            list1.next = mergeTwo(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwo(list1, list2.next);
            return list2;
        }
    }
}
