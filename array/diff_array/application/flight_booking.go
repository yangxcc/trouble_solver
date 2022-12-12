/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-12 21:30:23
 * @LastEditTime: 2022-12-12 22:14:40
 */
package application

import (
	"fmt"
	lib "trouble_solver/array/diff_array/modify_array"
)

/**
题目描述
这里有 n 个航班，它们分别从 1 到 n 进行编号。
有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi]
意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。
*/
func corpFlightBookings(bookings [][]int, n int) []int {
	d := lib.InitDiff(n)

	for _, v := range bookings {
		// 主要区别在这里，他们都是从1开始的
		d.Update(v[0]-1, v[1]-1, v[2])
		fmt.Println(d.Diff)
	}
	return d.Recover()
}
