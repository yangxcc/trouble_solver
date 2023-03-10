package maximumsubarray

import "math"

func maxProduct(nums []int) int {
	n := len(nums)
	dpMax, dpMin := make([]int, n), make([]int, n)
	for i := 0; i < n; i++ {
		dpMax[i] = nums[i]
		dpMin[i] = nums[i]
	}

	ans := nums[0]

	for i := 1; i < n; i++ {
		dpMax[i] = maxBetweent3(dpMax[i], nums[i]*dpMax[i-1], nums[i]*dpMin[i-1])
		dpMin[i] = minBetweent3(dpMin[i], nums[i]*dpMin[i-1], nums[i]*dpMax[i-1])

		ans = max(dpMax[i], ans)
	}

	return ans
}

func maxBetweent3(a, b, c int) int {
	tmp := a
	if a < b {
		tmp = b
	}

	if tmp < c {
		return c
	}

	return tmp
}

func minBetweent3(a, b, c int) int {
	tmp := math.MaxInt
	if a < b {
		tmp = a
	}

	if tmp < c {
		return tmp
	}
	return c
}
