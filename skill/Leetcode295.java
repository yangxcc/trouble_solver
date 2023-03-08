/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-18 13:18:37
 * @LastEditTime: 2023-03-02 21:56:35
 */
package skill;

import java.util.Comparator;
import java.util.PriorityQueue;

import javafx.scene.layout.Priority;

/**
 * leetcode 295 hard 数据流的中位数
 * 
 * 中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
 * 例如 arr = [2,3,4] 的中位数是 3 。
 * 例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
 * 
 * 实现 MedianFinder 类:
 *  MedianFinder() 初始化 MedianFinder 对象。
 *  void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
 *  double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10^-5 以内的答案将被接受。
 */
public class Leetcode295 {
    // 通过维护两个优先级队列，大根堆和小根堆
    // 想象把一个大根堆从中间切开，上面的部分倒过来，变成小根堆，下面的部分仍然是大根堆
    private PriorityQueue<Integer> big;
    private PriorityQueue<Integer> small;

    public Leetcode295() {
        // 小的那部分构建大根堆
        this.small = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });

        // 大的那部分小根堆
        this.big = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        // 通过上面堆大小根堆作用的描述，能够知道大根堆的堆顶（最大值）是比小根堆的堆顶（最小值）要 不大于
        // 可以通过举例子，来写出下面的代码，无关num和堆顶元素的大小比较
        if (small.size() >= big.size()) {
            small.offer(num);
            big.offer(small.poll());
            // 这样还能保证small和big的大小差距不超过1
        } else {
            big.offer(num);
            small.offer(big.poll());
        }
    }
    
    public double findMedian() {
        // 两堆元素个数不同
        if (big.size() < small.size()) {
            return small.peek();
        } else if (big.size() > small.size()) {
            return big.peek();
        } else {
            // 两堆元素个数相同
            return (small.peek() + big.peek()) / 2.0;  // 这个地方不要2，使用2.0会自动转成double
        }
    }
}
