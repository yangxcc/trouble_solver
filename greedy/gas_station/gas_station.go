package gasstation

import "math"

/**
给定两个整数数组 gas 和 cost ，如果你可以绕环路行驶一周，
则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
*/

// 暴力解法，模拟法，leetcode中超出时间限制 35/37
func canCompleteCircuit(gas []int, cost []int) int {
	var restContainer int

	// 从第i个节点开始
	for i, gasC := range gas {
		restContainer = gasC - cost[i]
		startIdx := (i + 1) % len(gas)
		for restContainer > 0 && startIdx != i {
			restContainer += gas[startIdx] - cost[startIdx]
			startIdx = (startIdx + 1) % len(gas)
		}
		if restContainer >= 0 && startIdx == i {
			return i
		}
	}
	return -1
}

/**
思路：
情况一：如果sum(gas) < sum(cost)，这种情况下一定是不能跑完一圈的
情况二：rest[i] = gas[i]-cost[i]为一天剩下的油，i从0开始计算累加到最后一站，如果累加过程中没有出现负数，说明从0出发，油就没有断过，那么0就是起点
情况三：如果累加的最小值是负数，汽车就要从非0节点出发，从后向前，看哪个节点能这个负数填平，能把这个负数填平的节点就是出发节点
*/
func canCompleteCircuitw(gas []int, cost []int) int {
	curSum := 0
	min := math.MaxInt
	for i := 0; i < len(gas); i++ {
		curSum += (gas[i] - cost[i])
		if curSum < min {
			min = curSum
		}
	}
	// 情况1
	if curSum < 0 {
		return -1
	}
	// 情况2
	if min >= 0 {
		return 0
	}
	// 情况3
	for i := len(gas) - 1; i >= 0; i-- {
		min += (gas[i] - cost[i])
		if min >= 0 {
			return i
		}
	}
	return -1
}

// curSum+=gas[i]-cost[i]，一旦curSum<0，则证明[0,i]区间内的点都不能作为起点
func canCompleteCircuitGreedy(gas []int, cost []int) int {
	var curSum int
	var startIdx int
	var totalSum int
	for i := 0; i < len(gas); i++ {
		curSum += (gas[i] - cost[i])
		// 这里能够保证sum(gas) < sum(cost)的情况
		totalSum += (gas[i] - cost[i])
		if curSum < 0 {
			startIdx = i + 1 // 以i+1为起点
			curSum = 0       // 从0开始重新计算
		}
	}
	if totalSum < 0 {
		return -1
	}
	return startIdx
}
