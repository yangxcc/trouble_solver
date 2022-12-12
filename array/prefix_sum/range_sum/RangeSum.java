/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-12 19:30:36
 * @LastEditTime: 2022-12-12 19:56:50
 */
package array.prefix_sum.range_sum;

public class RangeSum{
    int[] prefixSum;

    public RangeSum(int[] nums) {
        int n = nums.length;
        // 在这里prefixSum[i]表示的是[left, right-1]区间内的和
        this.prefixSum = new int[n+1];

        prefixSum[0] = nums[0];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i-1] + nums[i-1];
        }
    }

    // 之所以使用一个n+1长度的前缀和数组，就是为了统一形式
    // 其实和go中的实现思想是一样的，只是go中固定的是right边界，Java中固定的是left边界
    public int sumRange(int left, int right) {
        return prefixSum[right + 1] - prefixSum[left];
    }
}