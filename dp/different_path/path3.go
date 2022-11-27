package differentpath

/**
在二维网格 grid 上，有 4 种类型的方格：
	1 表示起始方格。且只有一个起始方格。
	2 表示结束方格，且只有一个结束方格。
	0 表示我们可以走过的空方格。
	-1 表示我们无法跨越的障碍。
返回在四个方向（上、下、左、右）上行走时，从起始方格到结束方格的不同路径的数目。
每一个无障碍方格都要通过一次，但是一条路径中不能重复通过同一个方格。
*/
func uniquePathsIII(grid [][]int) int {
	startIdx, countof0 := []int{}, 0
	m, n := len(grid), len(grid[0])
	visited := make([][]bool, m)
	for i, _ := range visited {
		visited[i] = make([]bool, n)
	}

	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				startIdx = append(startIdx, []int{i, j}...)
			} else if grid[i][j] == 0 {
				countof0++
			}
		}
	}

	var ans int
	var backtrack func(i, j int, visited [][]bool, path []int)
	backtrack = func(i, j int, visited [][]bool, path []int) {
		if i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || grid[i][j] == -1 {
			return
		}

		// 这里一定要注意countof0要加上1才与path的长度相等，因为path中包含一个开头的0
		if grid[i][j] == 2 && len(path) == countof0+1 {
			ans++
			return
		}

		visited[i][j] = true
		path = append(path, grid[i][j])

		backtrack(i-1, j, visited, path)
		backtrack(i+1, j, visited, path)
		backtrack(i, j-1, visited, path)
		backtrack(i, j+1, visited, path)

		visited[i][j] = false
		path = path[:len(path)-1]
	}

	backtrack(startIdx[0], startIdx[1], visited, []int{})
	return ans
}

func uniquePathsIIISpaceWiser(grid [][]int) int {
	startIdx, countof0 := []int{}, 0
	m, n := len(grid), len(grid[0])
	// 不需要visited数组，可以直接使用grid的数字来表示是否访问的状态
	// visited := make([][]bool, m)
	// for i, _ := range visited {
	// 	visited[i] = make([]bool, n)
	// }

	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				startIdx = append(startIdx, []int{i, j}...)
			} else if grid[i][j] == 0 {
				countof0++
			}
		}
	}

	var ans int
	var backtrack func(i, j int, step int)
	backtrack = func(i, j, step int) {
		if i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == -1 {
			return
		}

		// 同理，step这里也多了一步，因为需要算上最后一步，grid[i][j]==2
		if grid[i][j] == 2 && step == countof0+1 {
			ans++
			return
		}

		tmp := grid[i][j] // 把[i,j]位置当前的值存下来
		grid[i][j] = -1   // 表示已经访问过了

		backtrack(i-1, j, step+1)
		backtrack(i+1, j, step+1)
		backtrack(i, j-1, step+1)
		backtrack(i, j+1, step+1)

		grid[i][j] = tmp
	}
	// var backtrack func(i, j int, visited [][]bool, path []int)
	// backtrack = func(i, j int, visited [][]bool, path []int) {
	// 	if i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || grid[i][j] == -1 {
	// 		return
	// 	}

	// 	if grid[i][j] == 2 && len(path) == countof0+1 {
	// 		ans++
	// 		return
	// 	}

	// 	visited[i][j] = true
	// 	path = append(path, grid[i][j])

	// 	backtrack(i-1, j, visited, path)
	// 	backtrack(i+1, j, visited, path)
	// 	backtrack(i, j-1, visited, path)
	// 	backtrack(i, j+1, visited, path)

	// 	visited[i][j] = false
	// 	path = path[:len(path)-1]
	// }

	backtrack(startIdx[0], startIdx[1], 0)
	return ans
}
