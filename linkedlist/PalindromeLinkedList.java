package linkedlist;

/**
 * leetcode 234 simple 回文链表
 * 
 * 给定一个链表的头节点，判断它是否为回文链表
 */
public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        /*
         * 思路：
         * 首先快慢指针，找到中点，然后将链表分成两个链
         * 反转后面的链
         * 进行比较
         */
        ListNode fast = head, slow = head;
        // 这里快慢指针的条件改动一下，由fast != null && fast.next != null 变成下面的
        // 如果是上面这样写，slow最后指向的恰好是中点的节点，slow.next就变成了后面的了，所以我需要让slow指向中间的前一个
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode after = slow.next;
        slow.next = null;
        
        ListNode reverseAfter = reverse(after);
        while (reverseAfter != null) {
            if (reverseAfter.val == head.val) {
                reverseAfter = reverseAfter.next;
                head = head.next;
            } else {
                return false;
            }
        }

        return true;
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode reverseN = reverse(head.next);
        head.next.next = head;
        head.next = null;

        return reverseN;
    }
}
