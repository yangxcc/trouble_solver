/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-15 10:59:58
 * @LastEditTime: 2023-01-16 09:48:57
 */
package queue

// 单调队列
type MonotoneQueue struct {
	queue []int
}

func InitQueue() MonotoneQueue {
	return MonotoneQueue{
		queue: []int{},
	}
}

func (mq *MonotoneQueue) Empty() bool {
	return len(mq.queue) == 0
}

func (mq *MonotoneQueue) Back() int {
	return mq.queue[len(mq.queue)-1]
}

func (mq *MonotoneQueue) Font() int {
	return mq.queue[0]
}

func (mq *MonotoneQueue) Push(item int) {
	for !mq.Empty() && mq.Back() < item {
		mq.queue = mq.queue[0 : len(mq.queue)-1]
	}
	mq.queue = append(mq.queue, item)
}

// 这里不能无脑pop，因为item可能在push的时候已经被pop出去了
func (mq *MonotoneQueue) Pop(item int) {
	if !mq.Empty() && mq.Font() == item {
		mq.queue = mq.queue[1:]
	}
}
