/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-03-29 16:31:57
 * @LastEditTime: 2023-03-31 20:26:50
 */
package linkedlist;
// 1234 单向链表 原地螺旋式重组 1423 12345 15243
// 逻辑正确，边界值
// 代码模块化、可维护性
// 空间复杂度O(1)，时间复杂度不可以O(n^2)
// 不能使用额外的数据结构，只能使用基本数据类型
public class ReorganizedLinkedList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        // head.next = node1;
        // node1.next = node2;
        // node2.next = node3;
        // node3.next = node4;

        ListNode ans = process(head);
        while (ans != null) {
            System.out.println(ans.val);
            ans = ans.next;
        }
    }


    public static ListNode process(ListNode head) {
        if (head == null || head.next == null) {
            System.out.println("链表长度小于等于1，无需进行操作");
            return head;
        }
        // 1. 快慢指针找到中点，分成两个部分
        ListNode[] parts = partition(head);
        ListNode first = parts[0];
        ListNode second = parts[1];

        // 2. 反转后半部分的链表
        ListNode reverseSecond = reverse(second);

        // 3. 交叉连接两部分的链表
        return mergeTwoList(first, reverseSecond);
    }

    private static ListNode[] partition(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // slow刚好是中点
        ListNode after = slow.next;
        slow.next = null;

        return new ListNode[]{head, after};
    }

    // 反转链表
    private static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode rev = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return rev;
    }

    private static ListNode mergeTwoList(ListNode first, ListNode second) {
        ListNode helper1 = new ListNode(-1);
        ListNode helper2 = new ListNode(-1);
        ListNode ans = first;
        while (first != null && second != null) {
            helper1 = first.next;
            helper2 = second.next;

            first.next = second;
            second.next = helper1;
            first = helper1;
            second = helper2;
        }

        return ans;
    }
}

class ListNode {
    ListNode next;
    int val;

    public ListNode(int _val) {
        this.val = _val;
        this.next = null;
    }
}