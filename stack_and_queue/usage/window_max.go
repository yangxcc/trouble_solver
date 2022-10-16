package usage

func maxSlidingWindow(nums []int, k int) []int {
	mq := InitQueue()
	var ans []int
	for i := 0; i < k; i++ {
		mq.Push(nums[i])
	}

	// 在这里不要忘记添加起始k个元素的最大值
	ans = append(ans, mq.Font())

	for i := k; i < len(nums); i++ {
		mq.Pop(nums[i-k])
		mq.Push(nums[i])
		ans = append(ans, mq.Font())
	}

	return ans
}

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
