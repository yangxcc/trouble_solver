/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-15 11:00:15
 * @LastEditTime: 2023-01-16 09:46:47
 */
package stack_and_queue.monotonous.queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 单调队列的模板
 * 
 * 单调队列的作用是既能够维护队列元素先进先出的顺序，又能够正确维护队列中所有元素的最值
 * 如果只需要维护队列中所有元素的最值，那么优先级队列PriorityQueue就能够很好的完成这项任务
 */

public class Template {
    private Deque<Integer> queue;

    public Template() {
        this.queue = new LinkedList<>();
    }

    // 单调队列push的过程相当于胖子压瘦子
    public void push(int x) {
        while (!queue.isEmpty() && queue.peekLast() < x) {
            // queue.removeLast();
            queue.pollLast();
        }
        queue.addLast(x);
    }

    // 有可能在push的过程中，pop过了
    public void pop(int x) {
        if (queue.peekFirst() == x) {
            // queue.pollFirst();
            queue.pop();
        }
    }

    public int max() {
        return queue.peekFirst();
    }
}
