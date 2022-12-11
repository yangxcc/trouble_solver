/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-11 10:11:11
 * @LastEditTime: 2022-12-11 10:34:48
 */
package slidewindow

func maximumSubarraySum(nums []int, k int) int64 {
	left, right, n := 0, 0, len(nums)
	memo := map[int]int{}

	sum, ans := int64(0), int64(0)
	for right < n {
		rightBoundEle := nums[right]
		memo[rightBoundEle]++
		sum += int64(rightBoundEle)
		right++

		if len(nums[left:right]) == k {
			if len(memo) == k {
				ans = max(ans, sum)
			}
		} else if len(nums[left:right]) > k {
			leftBoundEle := nums[left]
			sum -= int64(leftBoundEle)
			memo[leftBoundEle]--
			if memo[leftBoundEle] == 0 {
				delete(memo, leftBoundEle)
			}
			left++
			// 因为right和left都是一步步走的，所以只要是到了这里，len(nums[left:right]) == k+1，如果在这里不更新ans，那么后面都更新不到ans了
			//			if len(nums[left:right]) == k {
			if len(memo) == k {
				ans = max(ans, sum)
			}
			//			}
		}
	}
	return ans
}

func max(a, b int64) int64 {
	if a < b {
		return b
	}
	return a
}
