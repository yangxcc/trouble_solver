package candy

import "fmt"

/**
n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。

你需要按照以下要求，给这些孩子分发糖果：
	每个孩子至少分配到 1 个糖果。
	相邻两个孩子评分更高的孩子会获得更多的糖果。
请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目
*/
func candyV1(ratings []int) int {
	ans := len(ratings)
	// 左一遍，右一遍
	for i := 1; i < len(ratings); i++ {
		if ratings[i] > ratings[i-1] {
			ans++
		}
	}

	for i := len(ratings) - 1; i > 0; i-- {
		if ratings[i] > ratings[i-1] {
			ans++
		}
	}
	return ans
}

/**
v1版本的解法对于[1,3,2,2,1]这个case解决不通过，最小糖果数应该是7，这里v1方法统计出来是8
这里的问题就在于3既比1大，也比2大，这里加了两次，是不合适的，上面的糖果分配可以是[1,2,1,2,1]
*/
func candyV2(ratings []int) int {
	ans := 0
	record := make([]int, len(ratings))
	for i := 0; i < len(ratings); i++ {
		record[i] = 1
	}

	// 左一遍，右一遍
	for i := 0; i < len(ratings)-1; i++ {
		if ratings[i] > ratings[i+1] {
			record[i]++
		}
	}

	// 对于既大于左边，又大于右边的，只加一次
	for i := len(ratings) - 1; i > 0; i-- {
		if ratings[i] > ratings[i-1] && record[i] == 1 {
			record[i]++
		}
		ans += record[i]
	}
	return ans + record[0]
}

/**
v2版本的解法对于[1,2,87,87,87,2,1]不通过，不通过的原因在于87>2，但是根据v2解法，两人的糖果数还是一样的
*/
func candyV3(ratings []int) int {
	ans := 0
	record := make([]int, len(ratings))
	for i := 0; i < len(ratings); i++ {
		record[i] = 1
	}

	// 左一遍，右一遍
	for i := 1; i <= len(ratings)-1; i++ {
		if ratings[i] > ratings[i-1] {
			record[i] = record[i-1] + 1
		}
	}

	// 对于既大于左边，又大于右边的，只加一次
	for i := len(ratings) - 2; i >= 0; i-- {
		if ratings[i] > ratings[i+1] {
			record[i] = record[i+1] + 1
		}
		ans += record[i]
	}
	fmt.Println(record)
	return ans + record[len(ratings)-1]
}

// /**
// v3解法对于[1,3,4,5,2]不通过，因为从尾到头的遍历过程中，会将record[i]的值再次修改掉
// */
func candyV4(ratings []int) int {
	ans := 0
	record := make([]int, len(ratings))
	for i := 0; i < len(ratings); i++ {
		record[i] = 1
	}

	// 左一遍，右一遍
	for i := 1; i <= len(ratings)-1; i++ {
		if ratings[i] > ratings[i-1] {
			record[i] = record[i-1] + 1
		}
	}

	// 对于既大于左边，又大于右边的，只加一次
	for i := len(ratings) - 2; i >= 0; i-- {
		if ratings[i] > ratings[i+1] {
			record[i] = max(record[i+1]+1, record[i])
		}
		ans += record[i]
	}
	fmt.Println(record)
	return ans + record[len(ratings)-1]
}

func max(a, b int) int {
	if a < b {
		return b
	}
	return a
}
