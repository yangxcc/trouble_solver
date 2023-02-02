package bipartitegraph

func isBipartite(graph [][]int) bool {
	n := len(graph)
	visited := make([]bool, n)
	color := make([]bool, n)
	ans := true

	var dfs func(graph [][]int, start int)
	dfs = func(graph [][]int, start int) {
		if visited[start] || !ans {
			return
		}

		// 怎么最近还总是把这个给忘了，不应该啊！！🤬
		visited[start] = true

		for _, neighbor := range graph[start] {
			if visited[neighbor] {
				// 判断颜色是否相同
				if color[neighbor] == color[start] {
					ans = false
					return
				}
			} else {
				color[neighbor] = !color[start]
				dfs(graph, neighbor)
			}
		}
	}

	for i := 0; i < len(graph); i++ {
		dfs(graph, i)
	}

	return ans
}
