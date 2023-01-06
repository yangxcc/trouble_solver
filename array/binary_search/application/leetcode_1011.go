/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-06 14:26:40
 * @LastEditTime: 2023-01-06 16:18:23
 */
package application

func shipWithinDays(weights []int, days int) int {
	left, right := 0, 0
	for _, w := range weights {
		right += w
		left = max(left, w)
	}

	// var countingDays func(weights []int, capacity int) int
	countingDays := func(weights []int, capacity int) int {
		days := 0
		for i := 0; i < len(weights); {
			tmpCapacity := capacity
			for i < len(weights) {
				tmpCapacity -= weights[i]
				if tmpCapacity < 0 {
					break
				}
				i++
			}
			days++
		}
		return days
	}

	for left <= right {
		mid := left + (right-left)/2

		if countingDays(weights, mid) == days {
			right = mid - 1
		} else if countingDays(weights, mid) < days {
			right = mid - 1
		} else if countingDays(weights, mid) > days {
			left = mid + 1
		}
	}

	return left
}

func max(a, b int) int {
	if a < b {
		return b
	}
	return a
}
