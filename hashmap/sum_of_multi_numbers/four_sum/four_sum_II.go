package foursum

func fourSumCount(nums1 []int, nums2 []int, nums3 []int, nums4 []int) int {
	var count int
	m := map[int]int{}
	for _, v1 := range nums1 {
		for _, v2 := range nums2 {
			m[v1+v2]++
		}
	}
	// 相当于转换了一下，将四数之和，转换成了两数之和
	// 如果0-(v3+v4)出现过，说明存在nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
	for _, v3 := range nums3 {
		for _, v4 := range nums4 {
			count += m[-v3-v4]
		}
	}
	return count
}
