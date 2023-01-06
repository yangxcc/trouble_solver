/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-06 21:25:58
 * @LastEditTime: 2023-01-06 22:03:03
 */
package application

func minEatingSpeed(piles []int, h int) int {
	minEatSpeed, maxEatSpeed := 1, 0
	for _, v := range piles {
		maxEatSpeed = max(maxEatSpeed, v)
	}

	findH := func(piles []int, eatSpeed int) int64 {
		var h int64
		for _, p := range piles {
			h += int64(p / eatSpeed)
			if p%eatSpeed != 0 {
				h++
			}
		}
		return h
	}

	for minEatSpeed <= maxEatSpeed {
		mid := minEatSpeed + (maxEatSpeed-minEatSpeed)/2
		if findH(piles, mid) == int64(h) {
			maxEatSpeed = mid - 1
		} else if findH(piles, mid) < int64(h) {
			maxEatSpeed = mid - 1
		} else if findH(piles, mid) > int64(h) {
			minEatSpeed = mid + 1
		}
	}

	return minEatSpeed
}
