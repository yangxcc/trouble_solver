package sudoku

/**
数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
*/
// 这种解法是错误的，跟N皇后不一样，因为N皇后问题只需要在每一行中添加一个皇后，所以我们只需要一层for循环遍历一行，递归来遍历列
// 而解数独在一行的所有地方都要放上元素，所以是二维递归，他的递归树比N皇后要大得多
func solveSudoku(board [][]byte) {
	nums := []byte{'1', '2', '3', '4', '5', '6', '7', '8', '9'}
	// 错误的写法
	var backtrack func(row int)
	backtrack = func(row int) {
		if row == len(board) {
			return
		}

		// 列
		for i := 0; i < 9; i++ {
			if board[row][i] == '.' {
				for _, v := range nums {
					if isValid(board, v, row, i) {
						board[row][i] = v
						backtrack(row + 1)
						board[row][i] = '.'
					} else {
						continue
					}
				}
			}
		}
	}
	backtrack(0)
}

func isValid(board [][]byte, val byte, row, col int) bool {
	// 行里是否有重复
	for i := 0; i < 9; i++ {
		if board[row][i] == val {
			return false
		}
	}

	// 列里是否有重复
	for i := 0; i < 9; i++ {
		if board[i][col] == val {
			return false
		}
	}

	// 所属3×3的范围内是否有重复值
	row = (row / 3) * 3
	col = (col / 3) * 3
	for i := row; i <= row+2; i++ {
		for j := col; j <= col+2; j++ {
			if board[i][j] == val {
				return false
			}
		}
	}
	return true
}

func solveSudoku2(board [][]byte) {
	var backtracking func(board [][]byte) bool
	// 本题递归不用终止条件，解数独是要遍历整个树形结构寻找可能的叶子节点就立刻返回。
	backtracking = func(board [][]byte) bool {
		// 行
		for i := 0; i < 9; i++ {
			for j := 0; j < 9; j++ {
				if board[i][j] != '.' {
					continue
				}

				for v := '1'; v <= '9'; v++ {
					if isValid(board, byte(v), i, j) {
						board[i][j] = byte(v)
						if backtracking(board) {
							return true
						}
						board[i][j] = '.'
					}
				}
				// 从1-9每个数都试了一遍之后没有正确答案，返回false
				return false
			}
		}
		return true // 遍历完没有返回false，说明找到了合适棋盘位置了
	}
	backtracking(board)
}
