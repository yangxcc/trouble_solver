/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-12 19:30:28
 * @LastEditTime: 2022-12-12 19:49:55
 */
package rangesum

/**
给定一个整数数组  nums，处理以下类型的多个查询:
计算索引 left 和 right （包含 left 和 right）之间的 nums 元素的 和 ，其中 left <= right
实现 NumArray 类：

NumArray(int[] nums) 使用数组 nums 初始化对象
int sumRange(int i, int j) 返回数组 nums 中索引 left 和 right 之间的元素的 总和 ，包含 left 和 right 两点（也就是 nums[left] + nums[left + 1] + ... + nums[right] )
*/

type NumArray struct {
	// 维护一个前缀和数组，predixSum[i]表示的是以i结尾，[0...i]的和
	PrefixSum []int
}

// 前缀和的写法，一个和nums相同长度的数组
func Constructor(nums []int) NumArray {
	n := len(nums)

	prefixSum := make([]int, n)
	prefixSum[0] = nums[0]
	for i := 1; i < n; i++ {
		prefixSum[i] = prefixSum[i-1] + nums[i]
	}

	return NumArray{PrefixSum: prefixSum}
}

func (this *NumArray) SumRange(left int, right int) int {
	if left == 0 {
		return this.PrefixSum[right]
	}
	return this.PrefixSum[right] - this.PrefixSum[left-1]
}
