package convert

import (
	"trouble_solver/stack_and_queue/container"
)

type MyQueue struct {
	stackIn  container.Stack[int]
	stackOut container.Stack[int]
}

func Constructor() MyQueue {
	return MyQueue{
		stackIn:  container.Stack[int]{},
		stackOut: container.Stack[int]{},
	}
}

func (this *MyQueue) Push(x int) {
	this.stackIn.Push(x)
}

func (this *MyQueue) Pop() int {
	this.moveElements()

	return this.stackOut.Pop()
}

func (this *MyQueue) Peek() int {
	this.moveElements()

	return this.stackOut.Top()
}

func (this *MyQueue) Empty() bool {
	return this.stackIn.Empty() && this.stackOut.Empty()
}

func (this *MyQueue) moveElements() {
	if this.stackOut.Empty() {
		for !this.stackIn.Empty() {
			this.stackOut.Push(this.stackIn.Pop())
		}
	}
}
