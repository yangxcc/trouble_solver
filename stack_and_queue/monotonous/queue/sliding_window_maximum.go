/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-16 09:35:11
 * @LastEditTime: 2023-01-16 09:49:21
 */
package queue

func maxSlidingWindow(nums []int, k int) []int {
	mq := InitQueue()
	var ans []int
	for i := 0; i < k; i++ {
		mq.Push(nums[i])
	}
	ans = append(ans, mq.Font())
	for i := k; i < len(nums); i++ {
		mq.Pop(nums[i-k])
		mq.Push(nums[i])
		ans = append(ans, mq.Font())
	}

	return ans
}
