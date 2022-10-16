package usage

import (
	"trouble_solver/stack_and_queue/container"
)

func RemoveDuplicates(s string) string {
	stack := container.Stack[string]{}
	b := []byte(s)
	for _, v := range b {
		if !stack.Empty() && stack.Top() == string(v) {
			stack.Pop()
		} else {
			stack.Push(string(v))
		}
	}

	count := stack.Length()
	ans := make([]byte, count)
	for i := count - 1; i >= 0; i-- {
		ans[i] = []byte(stack.Pop())[0]
	}
	return string(ans)
}
