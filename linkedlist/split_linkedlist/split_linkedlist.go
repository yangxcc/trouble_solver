/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-30 11:33:14
 * @LastEditTime: 2023-03-01 14:26:20
 */
package splitlinkedlist

import (
	. "trouble_solver/linkedlist"
)

/**
给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
你应当 保留 两个分区中每个节点的初始相对位置。
*/
// 对链表进行partition，先将链表拆成两个（小于的部分和大于等于的部分），然后再将两个链表合并
func partition(head *ListNode, x int) *ListNode {
	if head == nil {
		return nil
	}

	dummy1, dummy2 := &ListNode{}, &ListNode{}
	p1, p2 := dummy1, dummy2
	cur := head

	for cur != nil {
		if cur.Val < x {
			p1.Next = cur
			p1 = p1.Next
		} else {
			p2.Next = cur
			p2 = p2.Next
		}
		// 这里需要注意将每个节点后面的指针去掉，因为如果不去掉，dummy指针去遍历的时候会死循环
		tmp := cur.Next
		cur.Next = nil
		cur = tmp
	}

	p1.Next = dummy2.Next

	return dummy1.Next
}
