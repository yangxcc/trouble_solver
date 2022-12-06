## 0-1背包
一般情况下，背包问题通常是应用题，我们需要自己将题目转换，这里先把纯0-1背包的代码写一下

0-1背包问题的定义：有n件物品和一个最大容量为w的背包，第i件物品的重量是`weight[i]`，价值是`value[i]`，**每件物品只能使用一次**，求解将哪些物品装入背包里物品价值总和最大？

这道题目 其实可以使用 **回溯** 穷举出来，因为对于每一个物品都只有两个状态：取或者不取，因此使用回溯的时间复杂度为 $O(2^n)$，n为物品的个数

因为回溯的时间复杂度很高，所以我们需要使用动态规划来优化

**0-1背包之所以称之为0-1背包，是因为每个物品的数量是确定的，而且是只有一个，而完全背包问题中的物品数量是无限的**

```
// bagweight是背包的最大重量
func packageProblemFor01(weight, value []int, bagweight int) int {
	n := len(value)
	// dp[i][j]表示的是当背包重量为j的时候，前i个物品能达到的最大重量
	dp := make([][]int, n+1)
	for i, _ := range dp {
		dp[i] = make([]int, bagweight+1)
	}
	// base case，dp[0][...]=0, dp[...][0]=0
	for i := 1; i <= n; i++ {
		for j := 1; j <= bagweight; j++ {
			if j-weight[i-1] < 0 {
				// 只能不把物品放入背包
				dp[i][j] = dp[i-1][j]
			} else {
				// 是否选择第i-1个物品
				dp[i][j] = max(dp[i-1][j-weight[i]]+value[i-1], dp[i-1][j])
			}
		}
	}
	return dp[n][bagweight]
}

func max(a, b int) int {
	if a < b {
		return b
	}
	return a
}
```

对于物品和背包的遍历顺序也是很有讲究的：

- 如果求组合数就是外层for循环遍历物品，内层for遍历背包。
- 如果求排列数就是外层for遍历背包，内层for循环遍历物品。


状态压缩这一轮先不看了，状态压缩应该是有窍门，先正序遍历物品，后倒叙遍历背包（maybe😂）