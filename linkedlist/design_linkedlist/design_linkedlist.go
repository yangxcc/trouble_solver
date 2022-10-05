package designlinkedlist

type MyLinkedList struct {
	dummy *Node
}

// 定义节点类型
type Node struct {
	Val  int
	Pre  *Node
	Next *Node
}

func Constructor() MyLinkedList {
	dummy := &Node{
		Val:  -1,
		Pre:  nil,
		Next: nil,
	}
	dummy.Next = dummy
	dummy.Pre = dummy
	return MyLinkedList{dummy}
}

func (this *MyLinkedList) Get(index int) int {
	head := this.dummy.Next
	for head != this.dummy && index > 0 {
		head = head.Next
		index--
	}

	// 索引无效
	if index > 0 {
		return -1
	}
	return head.Val
}

func (this *MyLinkedList) AddAtHead(val int) {
	dummy := this.dummy
	head := &Node{
		Val:  val,
		Pre:  dummy,
		Next: dummy.Next,
	}
	dummy.Next.Pre = head
	dummy.Next = head
}

func (this *MyLinkedList) AddAtTail(val int) {
	dummy := this.dummy
	rear := &Node{
		Val:  val,
		Pre:  dummy.Pre,
		Next: dummy,
	}

	dummy.Pre.Next = rear
	dummy.Pre = rear
}

// 在链表的第index个节点之前添加值
func (this *MyLinkedList) AddAtIndex(index int, val int) {
	head := this.dummy.Next
	for head != this.dummy && index > 0 {
		head = head.Next
		index--
	}
	if index > 0 {
		return
	}

	node := &Node{
		Val:  val,
		Pre:  head.Pre,
		Next: head,
	}
	head.Pre.Next = node
	head.Pre = node
}

func (this *MyLinkedList) DeleteAtIndex(index int) {
	if this.dummy == this.dummy.Next {
		return
	}

	head := this.dummy.Next
	/*
		为什么这里是head.Next而不是head
		比如目前是dummy -> 2，index=1，这种情况下就不行
	*/
	for head.Next != this.dummy && index > 0 {
		head = head.Next
		index--
	}
	if index == 0 {
		head.Pre.Next = head.Next
		head.Next.Pre = head.Pre
		head.Pre = nil
		head.Next = nil
	}
}
