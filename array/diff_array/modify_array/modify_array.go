/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-12 21:04:52
 * @LastEditTime: 2022-12-12 22:09:24
 */
package modifyarray

/**
问题描述
假设你有一个长度为 n 的数组，初始情况下所有的数字均为 0，你将会被给出 k​​​​​​​ 个更新的操作。
其中，每个操作会被表示为一个三元组：[startIndex, endIndex, inc]，
你需要将子数组 A[startIndex ... endIndex]（包括 startIndex 和 endIndex）增加 inc。
请你返回 k 次操作后的数组。
*/
func getModifiedArray(length int, updates [][]int) []int {
	d := InitDiff(length)
	for _, updateVal := range updates {
		d.Update(updateVal[0], updateVal[1], updateVal[1])
	}

	return d.Recover()
}

// 差分数组结构体
type Difference struct {
	Diff    []int
	Update  func(left, right, val int)
	Recover func() []int
}

func InitDiff(length int) Difference {
	d := Difference{}
	d.Diff = make([]int, length)
	// d.Diff[0] = 0

	// 以val值，更新[left, right]区间内的数据
	d.Update = func(left, right, val int) {
		d.Diff[left] += val
		if right+1 < length {
			d.Diff[right+1] -= val
		}
	}

	d.Recover = func() []int {
		ans := make([]int, length)
		ans[0] = d.Diff[0]
		for i := 1; i < length; i++ {
			ans[i] = d.Diff[i] + ans[i-1]
		}
		return ans
	}

	return d
}
