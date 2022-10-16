package parentheses

import (
	"trouble_solver/stack_and_queue/container"
)

func isValid(s string) bool {
	stack := container.Stack[string]{}
	b := []byte(s)

	// 遇到左括号入栈，右括号出栈
	for _, v := range b {
		if v == '(' || v == '[' || v == '{' {
			stack.Push(string(v))
		} else {
			if stack.Empty() || stack.Pop() != want(v) {
				return false
			}
		}
	}
	return stack.Length() == 0
}

func want(key byte) string {
	switch key {
	case ')':
		return "("
	case ']':
		return "["
	case '}':
		return "{"
	}

	return ""
}
