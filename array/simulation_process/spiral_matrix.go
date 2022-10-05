package simulationprocess

func generateMatrix(n int) [][]int {
	up, down, left, right := 0, n-1, 0, n-1

	ans := make([][]int, n)
	for i := 0; i < n; i++ {
		ans[i] = make([]int, n)
	}

	cur := 1

	for cur <= n*n {
		for i := left; i <= right; i++ {
			ans[up][i] = cur
			cur++
		}
		up++

		for i := up; i <= down; i++ {
			ans[i][right] = cur
			cur++
		}
		right--

		for i := right; i >= left; i-- {
			ans[down][i] = cur
			cur++
		}
		down--

		for i := down; i >= up; i-- {
			ans[i][left] = cur
			cur++
		}
		left++
	}
	return ans
}
