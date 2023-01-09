/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-05 19:32:06
 * @LastEditTime: 2023-01-09 11:22:47
 */
package reverselinkedlist

import (
	. "trouble_solver/linkedlist"
)

/*
	反转列表总共有两种方法：双指针和递归
*/

// 双指针
func reverseListWithDoublePointer(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		// 没有或者只有一个节点
		return nil
	}
	var pre *ListNode = nil
	var cur *ListNode = head
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
func reverseListWithRecursion(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}

	recursion := reverseListWithRecursion(head.Next)
	head.Next.Next = head
	head.Next = nil
	return recursion
}

func reverseList2(head *ListNode, left int, right int) *ListNode {
	reverseNext := &ListNode{}
	var reverseNFromBegin func(head *ListNode, n int) *ListNode
	reverseNFromBegin = func(head *ListNode, n int) *ListNode {
		if n <= 1 {
			reverseNext = head.Next
			return head
		}

		reverseN := reverseNFromBegin(head.Next, n-1)
		head.Next.Next = head
		head.Next = reverseNext
		return reverseN
	}

	if left == 1 {
		return reverseNFromBegin(head, right)
	}

	head.Next = reverseList2(head.Next, left-1, right-1)
	return head
}
