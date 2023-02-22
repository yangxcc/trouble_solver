package stack_and_queue.convert;

import java.util.Stack;

public class Stack2Queue {
    private Stack<Integer> stack4Push;
    private Stack<Integer> stack4Pop;

    public Stack2Queue() {
        stack4Push = new Stack<>();
        stack4Pop = new Stack<>();
    }

    public void push(int x) {
        stack4Push.push(x);
    }

    public void pop() {
        if (stack4Pop.isEmpty()) {
            if (stack4Push.isEmpty()) {
                throw new RuntimeException();
            }
            while (!stack4Push.isEmpty()) {
                stack4Pop.push(stack4Push.pop());
            }
        } 
        stack4Pop.pop();
    }

    public boolean isEmpty() {
        return stack4Pop.size() == 0 && stack4Push.size() == 0;
    }
}
