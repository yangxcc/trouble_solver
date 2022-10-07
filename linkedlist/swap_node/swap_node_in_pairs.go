package swapnode

import (
	"trouble_solver/linkedlist"
)

// 两两交换相邻节点
// 1->2->3->4  变成 2->1->4->3
// 同样是两种方式，双指针和递归
func swapPairsWithRecursion(head *linkedlist.ListNode) *linkedlist.ListNode {
	if head == nil || head.Next == nil {
		return head
	}
	next := head.Next
	recursion := swapPairsWithRecursion(next.Next)
	head.Next = recursion
	next.Next = head
	return next
}

/*
	可以看到这道题是将偶数位的节点放在前面，奇数位的节点放到偶数点的后面
	这道题目的双指针的步骤如下：
	1. 将奇数位的节点和偶数位的节点分别拿出来（快慢指针）
	2. 将上面的两个链表合并起来
*/
func swapPairsWithDoublePointer(head *linkedlist.ListNode) *linkedlist.ListNode {
	if head == nil || head.Next == nil {
		return head
	}
	// 使用双指针将整条链拆开，奇数位和偶数位
	odd, even, evenHelper := head, head.Next, head.Next
	for even != nil {
		odd.Next = even.Next
		odd = odd.Next
		even.Next = odd
		even = even.Next
	}

	return merge(head, evenHelper)
}

// 奇数位的链表长度 >= 偶数位的链表长度
func merge(odd, even *linkedlist.ListNode) *linkedlist.ListNode {
	ans := even
	for odd != nil && even != nil {
		evenNext := even.Next
		oddNext := odd.Next
		even.Next = odd
		even = evenNext
		if even == nil {
			break
		}
		odd.Next = evenNext
		odd = oddNext
	}
	return ans
}

// 使用双指针也可以直接在链表上进行操作，需要使用dummy节点
// 这种方式需要画一画，单纯想没想出来
func swapPairsWithDoublePointerAndDummy(head *linkedlist.ListNode) *linkedlist.ListNode {
	dummy := &linkedlist.ListNode{
		Next: head,
	}

	pre := dummy
	for head != nil && head.Next != nil {
		pre.Next = head.Next
		nxt := head.Next.Next
		head.Next.Next = head
		head.Next = nxt
		pre = head
		head = nxt
	}

	return dummy.Next
}
