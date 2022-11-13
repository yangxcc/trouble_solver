package arrangetrip

import (
	"sort"
)

func findItinerary(tickets [][]string) []string {
	ans := [][]string{}
	var backtrack func(tickets [][]string, path []string, target string, visited []bool)
	backtrack = func(tickets [][]string, path []string, target string, visited []bool) {
		if len(path) == len(tickets)+1 {
			ans = append(ans, path)
			return
		}

		for i := 0; i < len(tickets); i++ {
			if !visited[i] && tickets[i][0] == target {
				path = append(path, tickets[i]...)
				visited[i] = true

				backtrack(tickets, path, tickets[i][1], visited)

				path = path[:len(path)-2]
				visited[i] = false
			} else {
				continue
			}
		}
	}

	backtrack(tickets, []string{}, "JFK", []bool{})
	return filterByDictSort(ans)
}

func filterByDictSort(paths [][]string) []string {
	tmp := []string{}
	for _, p := range paths {
		tmp = append(tmp, p...)
	}
	sort.Strings(tmp)
	// 每三个为一组，对tmp[0]进行分割
	b := []byte(tmp[0])
	tmp = []string{}
	for i := 0; i < len(b); i = i + 3 {
		if i+3 > len(b) {
			tmp = append(tmp, string(b[i:]))
		} else {
			tmp = append(tmp, string(b[i:i+3]))
		}
	}
	return tmp
}
