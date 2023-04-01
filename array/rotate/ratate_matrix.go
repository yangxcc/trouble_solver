package rotate

func rotate(matrix [][]int) {
	n := len(matrix)
	var levelRotate func(matrix [][]int)
	levelRotate = func(matrix [][]int) {
		up := 0
		down := n - 1

		for up < down {
			for i := 0; i < n; i++ {
				tmp := matrix[up][i]
				matrix[up][i] = matrix[down][i]
				matrix[down][i] = tmp
			}
			up++
			down--
		}
	}

	var diagonalRotate func(matrix [][]int)
	diagonalRotate = func(matrix [][]int) {
		for i := 0; i < n; i++ {
			for j := 0; j < i; j++ {
				tmp := matrix[i][j]
				matrix[i][j] = matrix[j][i]
				matrix[j][i] = tmp
			}
		}
	}

	levelRotate(matrix)
	diagonalRotate(matrix)
}
