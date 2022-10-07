package cyclelinkedlist

import "trouble_solver/linkedlist"

// 判断链表中是否有环
// fast每次走两步，slow每次走一步，如果链表中有环，那么fast和slow必定会在环中的某个节点处相遇
func hasCycle(head *linkedlist.ListNode) bool {
	fast, slow := head, head
	for fast != nil && fast.Next != nil {
		fast = fast.Next.Next
		slow = slow.Next
		if fast == slow {
			return true
		}
	}
	return false
}

// 找到环的起始点
func detectCycle(head *linkedlist.ListNode) *linkedlist.ListNode {
	fast, slow := head, head
	for fast != nil && fast.Next != nil {
		fast = fast.Next.Next
		slow = slow.Next
		if fast == slow {
			fast = head
			for fast != slow {
				fast = fast.Next
				slow = slow.Next
			}
			return fast
		}
	}
	return nil
}
