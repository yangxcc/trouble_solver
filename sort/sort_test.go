/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-13 17:31:25
 * @LastEditTime: 2022-12-26 21:22:57
 */
package sort_test

import (
	"fmt"
	"testing"
	"trouble_solver/sort/bubble"
	"trouble_solver/sort/heap"
	"trouble_solver/sort/insert"
	"trouble_solver/sort/merge"
	"trouble_solver/sort/quick"
	selectSort "trouble_solver/sort/select"
)

func TestBubbleSort(t *testing.T) {
	nums := []int{1, 5, 2, 3, 67, 9, -1}
	i := bubble.BubbleSort(nums)
	fmt.Println(i)

	j := selectSort.SelectSort(nums)
	fmt.Println(j)

	i2 := insert.InsertSort(nums)
	fmt.Println(i2)

	i3 := merge.MergeSortBetter(nums)
	fmt.Println(i3)

	i4 := quick.QuickSort(nums)
	fmt.Println(i4)

	i5 := heap.HeapSort(nums)
	fmt.Println(i5)
}
