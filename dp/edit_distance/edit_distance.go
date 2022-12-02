package editdistance

/**
给你两个单词word1和word2，请返回将word1转换成word2所使用的最少操作数。
你可以对一个单词进行如下三种操作：
	插入一个字符
	删除一个字符
	替换一个字符
*/
func minDistance(word1 string, word2 string) int {
	m, n := len(word1), len(word2)
	memo := make([][]int, m)
	for i, _ := range memo {
		memo[i] = make([]int, n)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			memo[i][j] = -1
		}
	}
	// 递归函数的意义是 求s1[0...i]和s2[0...j]的最小编辑距离
	var process func(s1 string, i int, s2 string, j int) int
	process = func(s1 string, i int, s2 string, j int) int {
		if i == -1 {
			return j + 1
		}
		if j == -1 {
			return i + 1
		}
		if memo[i][j] != -1 {
			return memo[i][j]
		}

		// 当两个字符相等的时候，不需要进行任何操作
		if s1[i] == s2[j] {
			memo[i][j] = process(s1, i-1, s2, j-1)
		} else {
			memo[i][j] = min(
				process(s1, i-1, s2, j),   // 删除
				process(s1, i, s2, j-1),   // 添加
				process(s1, i-1, s2, j-1), // 替换
			) + 1
		}
		return memo[i][j]
	}

	return process(word1, m-1, word2, n-1)
}

func minDistanceWithDPArray(word1, word2 string) int {
	m, n := len(word1), len(word2)
	// dp[i][j]的含义是word1[0..i]和word2[0...j]的编辑距离
	dp := make([][]int, m+1)
	for i, _ := range dp {
		dp[i] = make([]int, n+1)
	}
	// 初始化：如果word1==""，word2不为空
	for i := 0; i <= n; i++ {
		dp[0][i] = i
	}
	// 如果word1不为空，word2==""
	for i := 0; i <= m; i++ {
		dp[i][0] = i
	}

	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if word1[i-1] == word2[j-1] {
				dp[i][j] = dp[i-1][j-1]
			} else {
				dp[i][j] = min(dp[i-1][j], dp[i-1][j-1], dp[i][j-1]) + 1
			}
		}
	}
	return dp[m][n]
}

func min(a, b, c int) int {
	tmp := b
	if a < b {
		tmp = a
	}

	if tmp < c {
		return tmp
	}
	return c
}
