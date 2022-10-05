package slidewindow

// leetcode 209 middle 长度最小的子数组，最简单的滑动窗口题目
func minSubArrayLen(target int, nums []int) int {
	left, right := 0, 0
	cur, ans := 0, len(nums)+1

	for right < len(nums) {
		cur += nums[right]
		right++

		// 左闭右开区间
		for cur >= target {
			if right-left < ans {
				ans = right - left
			}
			cur -= nums[left]
			left++
		}
	}
	if ans == len(nums)+1 {
		return 0
	}
	return ans
}
