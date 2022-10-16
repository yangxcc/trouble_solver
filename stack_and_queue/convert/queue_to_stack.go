package convert

import "trouble_solver/stack_and_queue/container"

type MyStack struct {
	queue container.Queue[int]
}

func (this *MyStack) Constructor() MyStack {
	this.queue = container.Queue[int]{}
	return MyStack{
		queue: this.queue,
	}
}

func (this *MyStack) Push(item int) {
	this.queue.Push(item)
}

func (this *MyStack) Pop() int {
	this.moveElements()
	return this.queue.Pop()
}

func (this *MyStack) Top() int {
	this.moveElements()

	return this.queue.Top()
}

func (this *MyStack) Empty() bool {
	return this.queue.Empty()
}

func (this *MyStack) moveElements() {
	length := this.queue.Length()
	for i := 1; i < length; i++ {
		this.queue.Push(this.queue.Pop())
	}
}

/**
 * Your MyStack object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Push(x);
 * param_2 := obj.Pop();
 * param_3 := obj.Top();
 * param_4 := obj.Empty();
 */
