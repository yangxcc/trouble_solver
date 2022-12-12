差分数组（`nums[i]`和`nums[i-1]`的差值）的主要使用场景是频繁对原始数组的某个区间的元素进行增减

核心代码是
```go
// 构造差分数组
diff := make([]int, len(nums))
diff[0] = nums[0]

for i := 1; i < len(nums); i++ {
    diff[i] = nums[i] - nums[i-1]
}

// 还原数组
res := make([]int, len(nums))
res[0] = diff[0]
for i := 1; i < len(nums); i++ {
    res[i] = res[i-1] + diff[i]
}
```

如果我想要在[i,j]这个区间内对所有元素+3，借用差分数组，我们可以这样使用：
- `diff[i] += 3`，在差分数组上+3，这在还原过程中相当于nums[i...]这些元素都+3
- `diff[j+1] -= 3`，在差分数组上-3，这在还原过程中相当于nums[j+1...]这些元素都-3

根据上面的过程，相当于在$O(1)$的时间内修改了[i,j]之间的数据值

差分数组的完整代码见 [Go代码](./modify_array/modify_array.go) [Java代码](modify_array/ModifyArray.java)

应用示例：leetcode 1109 [航班预定统计](https://leetcode.cn/problems/corporate-flight-bookings/)