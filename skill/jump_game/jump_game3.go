/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-02-18 15:53:51
 * @LastEditTime: 2023-02-18 16:23:28
 */
package jumpgame

func canReach(arr []int, start int) bool {
	n := len(arr)
	visited := make([]bool, n)

	var dfs func(arr []int, idx int) bool
	dfs = func(arr []int, idx int) bool {
		if idx < 0 || idx >= len(arr) || visited[idx] {
			return false
		}

		if arr[idx] == 0 {
			return true
		}

		visited[idx] = true
		return dfs(arr, idx+arr[idx]) || dfs(arr, idx-arr[idx])
	}

	return dfs(arr, start)
}
