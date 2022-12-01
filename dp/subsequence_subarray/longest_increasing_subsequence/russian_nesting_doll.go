package longestincreasingsubsequence

import "sort"

/**
给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
*/
func maxEnvelopes(envelopes [][]int) int {
	// 先按照宽度升序排列，如果宽度相等，则按照高度降序排列
	// 宽度升序排列能够保证在w这个维度，我们只需要关注h就好了
	// h降序排列能够保证不会出现相同w的信封
	sort.Slice(envelopes, func(i, j int) bool {
		if envelopes[i][0] == envelopes[j][0] {
			return envelopes[i][1] > envelopes[j][1]
		}
		return envelopes[i][0] < envelopes[j][0]
	})

	// 排好序后，我们只需要再根据h来找最长递增序列，就又成了一维数组上的LIS问题
	dp := make([]int, len(envelopes))
	for i := 0; i < len(envelopes); i++ {
		dp[i] = 1
	}

	var ans int = 1
	for i := 1; i < len(envelopes); i++ {
		for j := 0; j < i; j++ {
			if envelopes[j][1] < envelopes[i][1] {
				dp[i] = max(dp[i], dp[j]+1)
				ans = max(dp[i], ans)
			}
		}
	}
	return ans
}

// 但是现在简单的排序+动态规划已经过不了了，85/87，超时

func maxEnvelopesWithBinarySort(envelopes [][]int) int {
	// 先按照宽度升序排列，如果宽度相等，则按照高度降序排列
	// 宽度升序排列能够保证在w这个维度，我们只需要关注h就好了
	// h降序排列能够保证不会出现相同w的信封
	sort.Slice(envelopes, func(i, j int) bool {
		if envelopes[i][0] == envelopes[j][0] {
			return envelopes[i][1] > envelopes[j][1]
		}
		return envelopes[i][0] < envelopes[j][0]
	})

	// 排好序后，我们只需要再根据h来找最长递增序列，就又成了一维数组上的LIS问题
	dp := make([]int, len(envelopes))
	for i := 0; i < len(envelopes); i++ {
		dp[i] = 1
	}

	var ans int = 1
	for i := 1; i < len(envelopes); i++ {
		for j := 0; j < i; j++ {
			if envelopes[j][1] < envelopes[i][1] {
				dp[i] = max(dp[i], dp[j]+1)
				ans = max(dp[i], ans)
			}
		}
	}
	return ans
}

// 使用二分，能够把时间复杂度从o(n^2)降到O(nlogn)
func lengthOfLISWithBinarySort(nums []int) int {
	n := len(nums)
	// 指派堆顶的元素是升序排列的
	top := make([]int, n)
	piles := 0 // 纸牌的堆数
	for i := 0; i < n; i++ {
		poker := nums[i] // 要处理的扑克牌
		left, right := 0, piles
		for left < right {
			mid := left + (right-left)/2
			if top[mid] >= poker {
				right = mid
			} else if top[mid] < poker {
				left = mid + 1
			}
		}

		if left == piles {
			// 没有找到适合的牌堆
			piles++
		}
		top[left] = poker
	}
	return piles
}
