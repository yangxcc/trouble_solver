import linkedlist.ListNode;

/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-07 13:56:19
 * @LastEditTime: 2022-12-30 10:00:18
 */
public class IsCycleLinkedlist {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                return true;
            }
        }
        return false;
    }


    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                fast = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        } 
        return null;
    }
}
