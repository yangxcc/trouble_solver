package usage

import (
	"fmt"
	"strconv"
	"trouble_solver/stack_and_queue/container"
)

/*
	根据逆波兰表达式求值
		输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
		输出：22
		解释：该算式转化为常见的中缀算术表达式为：
		((10 * (6 / ((9 + 3) * -11))) + 17) + 5
		= ((10 * (6 / (12 * -11))) + 17) + 5
		= ((10 * (6 / -132)) + 17) + 5
		= ((10 * 0) + 17) + 5
		= (0 + 17) + 5
		= 17 + 5
		= 22


	逆波兰表达式主要有以下两个优点：
		去掉括号后表达式无歧义
		适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中

*/
// 方法就是遇到数入栈，遇到符号，从栈中弹出两个数 RPN=reverse polish notation
func evalRPN(tokens []string) int {
	numStack := container.Stack[int]{}

	for _, s := range tokens {
		i, err := strconv.Atoi(s)
		if err == nil {
			numStack.Push(i)
		} else {
			a := numStack.Pop()
			b := numStack.Pop()
			switch s {
			case "+":
				numStack.Push(a + b)
			case "-":
				numStack.Push(b - a)
			case "*":
				numStack.Push(a * b)
			case "/":
				numStack.Push(b / a)
			}
		}
	}

	return numStack.Top()
}

func ForTest() {
	tokens := []string{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}
	i := evalRPN(tokens)
	fmt.Println(i)
}
