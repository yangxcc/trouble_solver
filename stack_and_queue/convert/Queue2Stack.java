package stack_and_queue.convert;

import java.util.ArrayDeque;
import java.util.Deque;

public class Queue2Stack {
    private Deque<Integer> queue;

    public Queue2Stack() {
        queue = new ArrayDeque<>();
    }

    public void push(int x) {
        queue.add(x);
    }

    public void pop() {
        for (int i = 0; i < queue.size() - 1; i++) {
            queue.add(queue.pop());
        }
        queue.pop();
    }

    public int top() {
        for (int i = 0; i < queue.size() - 1; i++) {
            queue.add(queue.pop());
        }
        
        return queue.getFirst();
    }

    public boolean isEmpty() {
        return queue.size() == 0;
    }
}
