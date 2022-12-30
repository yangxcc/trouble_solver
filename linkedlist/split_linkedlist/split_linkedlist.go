/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-30 11:33:14
 * @LastEditTime: 2022-12-30 11:58:36
 */
package splitlinkedlist

import . "trouble_solver/linkedlist"

/**
给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
你应当 保留 两个分区中每个节点的初始相对位置。
*/
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
		tmp := cur.Next
		cur.Next = nil
		cur = tmp
	}

	p1.Next = dummy2.Next

	return dummy1.Next
}
