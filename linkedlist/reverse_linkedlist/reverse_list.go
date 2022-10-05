package reverselinkedlist

import "trouble_solver/linkedlist"

/*
	反转列表总共有两种方法：双指针和递归
*/

// 双指针
func reverseListWithDoublePointer(head *linkedlist.ListNode) *linkedlist.ListNode {
	if head == nil || head.Next == nil {
		// 没有或者只有一个节点
		return nil
	}
	var pre *linkedlist.ListNode = nil
	var cur *linkedlist.ListNode = head
	for cur != nil {
		// 保存下当前节点后面的节点
		next := cur.Next
		cur.Next = pre
		pre = cur
		cur = next
	}
	// 这里一定不要定式思维，把head给写上了
	return pre
}

// 递归
func reverseListWithRecursion(head *linkedlist.ListNode) *linkedlist.ListNode {
	if head == nil || head.Next == nil {
		return head
	}

	recursion := reverseListWithRecursion(head.Next)
	head.Next.Next = head
	head.Next = nil
	return recursion
}
