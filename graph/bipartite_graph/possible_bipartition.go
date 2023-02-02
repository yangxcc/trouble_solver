/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-02-02 16:09:19
 * @LastEditTime: 2023-02-02 17:37:02
 */
package bipartitegraph

func possibleBipartition(n int, dislikes [][]int) bool {
	graph := buildGraph(dislikes, n)
	visited := make([]bool, n+1)
	color := make([]bool, n+1)
	ans := true

	var dfs func(graph [][]int, start int)
	dfs = func(graph [][]int, start int) {
		if visited[start] || !ans {
			return
		}

		visited[start] = true

		for _, neighbor := range graph[start] {
			if visited[neighbor] {
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

	for i := 1; i <= n; i++ {
		dfs(graph, i)
	}

	return ans
}

func buildGraph(dislikes [][]int, n int) [][]int {
	graph := make([][]int, n+1)
	for i := 0; i <= n; i++ {
		graph[i] = make([]int, 0)
	}

	for _, edge := range dislikes {
		from := edge[0]
		to := edge[1]
		graph[from] = append(graph[from], to)
		graph[to] = append(graph[to], from)
	}

	return graph
}
