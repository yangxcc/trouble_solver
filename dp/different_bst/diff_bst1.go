package differentbst

/**
给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
*/
func numTrees(n int) int {
	memo := make([][]int, n+2)
	for i, _ := range memo {
		memo[i] = make([]int, n+2)
	}
	// dp[i]定义为以i为根节点，能够构成的bst的个数，此时左子树是1-(i-1)，右子树是(i+1)-n
	// 注意根据这样的定义，最后结果不能是dp[i]，而是sum(dp[i])
	// process定义为从区间left到right能够构成bst的个数
	var process func(left, right int) int
	process = func(left, right int) int {
		if left >= right {
			// 因为后面是乘积，所以即使left>right，也不要返回0
			memo[left][right] = 1
			return 1
		}
		if memo[left][right] != 0 {
			return memo[left][right]
		}
		var ans int
		for i := left; i <= right; i++ {
			leftCount := process(left, i-1)
			rightCount := process(i+1, right)
			ans += leftCount * rightCount
		}
		memo[left][right] = ans
		return ans
	}

	return process(1, n)
}

// 直接递归是超时的，优化方法可以增加一个memo，来记录过程中dp[i]的值

// 修改dp数组的定义
func numTrees2(n int) int {
	// dp[i]表示的是i个数能够构成bst的种数
	dp := make([]int, n+1)
	dp[0], dp[1] = 1, 1
	for i := 2; i <= n; i++ {
		for j := 1; j <= i; j++ {
			// j-1 为j为头结点左子树节点数量，i-j 为以j为头结点右子树节点数量
			dp[i] += dp[j-1] * dp[i-j]
		}
	}

	return dp[n]
}
