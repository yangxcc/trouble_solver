/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-30 11:51:17
 * @LastEditTime: 2022-12-30 11:53:55
 */
package splitlinkedlist

import (
	"testing"

	. "trouble_solver/linkedlist"
)

func TestPartition(t *testing.T) {
	// 1,4,3,2,5,2
	head := &ListNode{Val: 1}
	node1 := &ListNode{Val: 4}
	node2 := &ListNode{Val: 3}
	node3 := &ListNode{Val: 2}
	node4 := &ListNode{Val: 5}
	node5 := &ListNode{Val: 2}
	head.Next = node1
	node1.Next = node2
	node2.Next = node3
	node3.Next = node4
	node4.Next = node5

	partition(head, 3)
}
