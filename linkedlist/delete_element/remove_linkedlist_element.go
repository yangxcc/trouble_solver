package deleteelement

import "trouble_solver/linkedlist"

// removeElements 移除链表中的重复元素，未使用dummy节点
func removeElements(head *linkedlist.ListNode, val int) *linkedlist.ListNode {
	if head == nil {
		return nil
	}

	// 头节点的移除和其他节点不同，头节点移除只需要向后移动一位就可以了
	for head != nil && head.Val == val {
		head = head.Next
	}

	pre, cur := &linkedlist.ListNode{}, head
	for cur != nil {
		if cur.Val == val {
			pre.Next = cur.Next
		} else {
			pre = cur
		}
		cur = cur.Next
	}

	return head
}

// removeElementsWithDummyNode 使用dummy节点。统一头节点和其他节点的删除方式
func removeElementsWithDummyNode(head *linkedlist.ListNode, val int) *linkedlist.ListNode {
	dummyHead := &linkedlist.ListNode{}
	dummyHead.Next = head
	cur := dummyHead
	for cur != nil && cur.Next.Next != nil {
		if cur.Next.Val == val {
			cur.Next = cur.Next.Next
		} else {
			cur = cur.Next
		}
	}
	return dummyHead.Next
}
