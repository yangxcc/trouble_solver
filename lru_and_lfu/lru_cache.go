/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-17 09:56:21
 * @LastEditTime: 2023-01-17 10:53:58
 */
package lruandlfu

/**
手动实现java中的LinkedHashMap
*/
// 双向链表中的节点类型
type Node struct {
	Prev *Node
	Next *Node
	Key  int
	Val  int
}

// 之所以使用双向链表，目的是各种链表操作 比如删除某个节点，其时间复杂度都为O(1)
type DoubleLinkedList struct {
	Size      int
	DummyHead *Node
	DummyTail *Node
}

func InitDoubleLinkedList() *DoubleLinkedList {
	head, tail := &Node{}, &Node{}
	head.Next = tail
	tail.Prev = head

	return &DoubleLinkedList{Size: 0, DummyHead: head, DummyTail: tail}
}

func (dll *DoubleLinkedList) addLast(node *Node) {
	node.Next = dll.DummyTail
	node.Prev = dll.DummyTail.Prev
	dll.DummyTail.Prev.Next = node
	dll.DummyTail.Prev = node
	dll.Size++
}

// 删除链表中的某个节点
func (dll *DoubleLinkedList) remove(node *Node) {
	node.Prev.Next = node.Next
	node.Next.Prev = node.Prev
	// 不要忘记元素个数减1
	dll.Size--
}

func (dll *DoubleLinkedList) removeFirst() *Node {
	if dll.DummyHead.Next == dll.DummyTail {
		return nil
	}

	first := dll.DummyHead.Next
	dll.remove(first)
	return first
}

// 因为题目中还要求O(1)的时间复杂度，所以还需要将双向链表和HashMap对应起来
// 即实现LinkedHashMap，本质上同时维护双向链表和哈希表
type LRUCache struct {
	Cache    *DoubleLinkedList
	Memo     map[int]*Node
	Capacity int
}

func Constructor(capacity int) LRUCache {
	return LRUCache{
		Cache:    InitDoubleLinkedList(),
		Memo:     map[int]*Node{},
		Capacity: capacity,
	}
}

func (this *LRUCache) Get(key int) int {
	if _, ok := this.Memo[key]; !ok {
		return -1
	}

	this.makeKeyFirst(key)
	return this.Memo[key].Val
}

func (this *LRUCache) Put(key int, value int) {
	if _, ok := this.Memo[key]; ok {
		this.Memo[key].Val = value
		this.makeKeyFirst(key)
	} else {
		// 不存在key
		node := &Node{Key: key, Val: value}
		if this.Capacity == this.Cache.Size {
			// 需要删除头部元素
			firstNode := this.Cache.removeFirst()
			delete(this.Memo, firstNode.Key)
		}
		this.Cache.addLast(node)
		this.Memo[key] = node
	}
}

func (this *LRUCache) makeKeyFirst(key int) {
	node := this.Memo[key]

	this.Cache.remove(node)
	this.Cache.addLast(node)
}
