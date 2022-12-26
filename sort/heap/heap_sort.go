/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-26 16:38:44
 * @LastEditTime: 2022-12-26 21:48:39
 */
package heap

import (
	"errors"
	"fmt"
	"math"
)

// TODO 代码是错误的，还没debug出来
func HeapSort(nums []int) []int {
	heap := Init(20)
	var err error
	for _, num := range nums {
		err = heap.put(num)
		if err != nil {
			panic(err.Error())
		}
	}

	fmt.Println(heap.Arr)

	for i := 0; i < len(nums); i++ {
		nums[i], err = heap.pop()
		if err != nil {
			panic(err.Error())
		}
	}

	return nums
}

type ArrayHeap struct {
	Arr      []int
	HeapSize int
	MaxSize  int
}

// 小根堆
func Init(maxSize int) *ArrayHeap {
	return &ArrayHeap{
		Arr:     make([]int, maxSize),
		MaxSize: maxSize,
	}
}

func (h *ArrayHeap) put(num int) error {
	if h.HeapSize == h.MaxSize {
		return errors.New("堆满了！")
	}

	h.Arr[h.HeapSize+1] = num
	h.HeapSize++

	curIdx := h.HeapSize
	h.swim(curIdx)
	return nil
}

func (h *ArrayHeap) swim(curIdx int) {
	for curIdx > 1 {
		father := curIdx >> 1
		if h.Arr[father] < h.Arr[curIdx] {
			h.Arr[father], h.Arr[curIdx] = h.Arr[curIdx], h.Arr[father]
			curIdx = father
		} else {
			break
		}
	}
}

func (h *ArrayHeap) pop() (int, error) {
	if h.HeapSize == 0 {
		return math.MinInt, errors.New("堆空了！")
	}
	minVal := h.Arr[1]
	h.Arr[1] = h.Arr[h.HeapSize]
	h.HeapSize--

	var sink func(curIdx int)
	sink = func(curIdx int) {
		for 2*curIdx <= h.HeapSize {
			child := 2 * curIdx
			if child+1 <= h.HeapSize && h.Arr[child+1] < h.Arr[child] {
				child += 1
			}

			if h.Arr[child] > h.Arr[curIdx] {
				break
			}

			h.Arr[curIdx], h.Arr[child] = h.Arr[child], h.Arr[curIdx]
			curIdx = child
		}
	}

	sink(1)

	return minVal, nil
}
