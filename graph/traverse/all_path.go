package traverse

func allPathsSourceTarget(graph [][]int) [][]int {
	ans := [][]int{}

	var dfs func(graph [][]int, start int, path []int)
	dfs = func(graph [][]int, start int, path []int) {
		path = append(path, start)

		if start == len(graph)-1 {
			ans = append(ans, copySlice(path))
			path = path[:len(path)-1]
			return
		}

		for _, neighbor := range graph[start] {
			dfs(graph, neighbor, path)
		}
		path = path[:len(path)-1]
	}

	dfs(graph, 0, []int{})

	return ans
}

func copySlice(a []int) []int {
	b := []int{}
	b = append(b, a...)
	return b
}
