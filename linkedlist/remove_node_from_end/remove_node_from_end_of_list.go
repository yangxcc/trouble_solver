package removenodefromend

import "trouble_solver/linkedlist"

// 删除链表倒数第n个节点
// 快慢指针，快指针先走n步，m为链表总长度，随后快慢指针同时走，当快指针都到头时，慢指针到达第n个节点位置
func removeNthFromEnd(head *linkedlist.ListNode, n int) *linkedlist.ListNode {
	fast, slow := head, head
	for i := 0; i < n; i++ {
		fast = fast.Next
		// 快指针先走n步
	}
	if fast == nil {
		return head.Next
	}

	// 这样slow指向的是要删除节点的前一个节点
	for fast.Next != nil {
		slow = slow.Next
		fast = fast.Next
	}
	slow.Next = slow.Next.Next
	return head
}

// 事实上，只要是存在特殊的返回情况，比如上面head和head.next，就可以使用dummy节点来统一输出
// 快慢指针+dummy
func removeNthFromEndWithDummy(head *linkedlist.ListNode, n int) *linkedlist.ListNode {
	dummy := &linkedlist.ListNode{
		Next: head,
	}

	cur, pre := head, dummy
	i := 1
	for cur != nil {
		cur = cur.Next
		if i > n {
			pre = pre.Next
		}
		i++
	}
	pre.Next = pre.Next.Next

	return dummy.Next
}
