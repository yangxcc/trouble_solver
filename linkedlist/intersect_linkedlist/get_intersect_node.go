package intersectlinkedlist

import "trouble_solver/linkedlist"

// 找出两个单链表相交的起始节点，如果两个链表没有交点，返回 null。
func getIntersectionNode(headA, headB *linkedlist.ListNode) *linkedlist.ListNode {
	if headA == nil || headB == nil {
		return nil
	}
	AHelper, BHelper := headA, headB
	for AHelper != BHelper {
		if AHelper == nil {
			AHelper = headB
		} else {
			AHelper = AHelper.Next
		}
		if BHelper == nil {
			BHelper = headA
		} else {
			BHelper = BHelper.Next
		}
	}
	return AHelper
}
