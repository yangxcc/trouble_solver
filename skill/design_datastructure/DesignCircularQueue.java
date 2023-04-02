package skill.design_datastructure;

/**
 * leetcode 622 middle 设计循环队列
 * 
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。
 * 在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，
 * 即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值
 * 
 * 也就是说，只有前面的元素空了的时候，才能够继续插入，满了之后就不能插入了，即不能覆盖
 */
public class DesignCircularQueue {
    // 循环队列的长度为k
    private int k;
    private int[] cache;
    private int head;
    private int tail;

    public DesignCircularQueue(int k) {
        this.k = k;
        this.cache = new int[k];
        this.head = 0; 
        this.tail = 0;
    }

    // 向队列中插入一个元素，插入成功则返回true
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        cache[tail % k] = value;
        tail++;
        return tail >= 0;
    }

    // 从队列中删除一个元素，删除成功则返回true
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        
        head++;
        return head >= 0;
    }

    // 取队首元素
    public int Front() {
        return isEmpty() ? -1 : cache[head % k];
    }

    // 取队尾元素
    public int Rear() {
        return isEmpty() ? -1 : cache[(tail - 1) % k];  // 一定要注意-1
    }

    // 是否为空
    public boolean isEmpty() {
        return head == tail;
    }

    // 是否满了
    public boolean isFull() {
        return tail - head == k;
    }
}
