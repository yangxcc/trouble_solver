package nqueens

func solveNQueens(n int) [][]string {
	// 初始化棋盘
	chessboard := make([][]string, n)
	for i := 0; i < n; i++ {
		chessboard[i] = make([]string, n)
	}
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			chessboard[i][j] = "."
		}
	}

	ans := [][]string{}
	// 从[row, col]kaishi
	var backtrack func(row, n int)
	backtrack = func(row, n int) {
		if row == n {
			// 把棋盘序列化下来
			ans = append(ans, serialize(chessboard))
			return
		}

		for j := 0; j < n; j++ {
			if isValid(row, j, n, chessboard) {
				chessboard[row][j] = "Q"
				backtrack(row+1, n)
				chessboard[row][j] = "."
			} else {
				continue
			}

		}
	}
	backtrack(0, n)
	return ans
}

// 在[row, col]位置能够满足要求：不能同行同列同斜线
func isValid(row, col, n int, chessboard [][]string) bool {
	// 不能同行，不能同列
	for i := 0; i < row; i++ {
		if chessboard[i][col] == "Q" {
			return false
		}
	}
	// 不能同斜线 45°和135°
	for i, j := row-1, col-1; i >= 0 && j >= 0; i, j = i-1, j-1 {
		if chessboard[i][j] == "Q" {
			return false
		}
	}
	for i, j := row-1, col+1; i >= 0 && j < n; i, j = i-1, j+1 {
		if chessboard[i][j] == "Q" {
			return false
		}
	}
	return true
}

func copy(a []string) (b []string) {
	b = append(b, a...)
	return
}

func serialize(chessboard [][]string) []string {
	var ans []string
	for _, rowVals := range chessboard {
		var tmp string
		for _, v := range rowVals {
			tmp += v
		}
		ans = append(ans, tmp)
	}
	return ans
}
