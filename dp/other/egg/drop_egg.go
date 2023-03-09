/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-03-09 16:13:32
 * @LastEditTime: 2023-03-09 16:41:13
 */
package egg

import "math"

/**
k枚鸡蛋，n层楼
已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。
每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。
如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。

请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
* */
func superEggDrop(k int, n int) int {
	memo := make([][]int, k+1)
	for i, _ := range memo {
		memo[i] = make([]int, n+1)
	}

	// 用k枚鸡蛋确定n层楼中的f的最小操作次数
	var dp func(k, n int) int
	dp = func(k, n int) int {
		if n == 0 {
			return 0
		}

		if k == 1 {
			return n
		}

		if memo[k][n] != 0 {
			return memo[k][n]
		}

		ans := math.MaxInt
		// for i := 3; i <= n; i++ {
		// 	// 从第i层楼上扔鸡蛋，有两种情况：鸡蛋碎了或者鸡蛋没碎
		// 	ans = min(ans, max(dp(k-1, i-1), dp(k, n-i)))
		// }

		// 使用二分搞一下
		left := 1
		right := n

		for left <= right {
			mid := left + (right-left)/2
			broken := dp(k-1, mid-1)
			nonBroken := dp(k, n-mid)
			if broken > nonBroken {
				ans = min(ans, broken)
				right = mid - 1
			} else {
				ans = min(ans, nonBroken)
				left = mid + 1
			}
		}

		memo[k][n] = ans

		return ans
	}

	return dp(k, n)

}

func min(a, b int) int {
	if a < b {
		return a
	}

	return b
}

func max(a, b int) int {
	if a > b {
		return a
	}

	return b
}
