/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-13 18:10:51
 * @LastEditTime: 2022-12-13 19:26:38
 */
package sort.insert;

public class InsertSort {
    public int[] sort(int []nums) {
        if (nums.length <= 1) {
            return nums;
        }

        for (int i = 1; i < nums.length; i++) {
            int curVal = nums[i];
            int pos = i-1;

            while (pos >= 0 && nums[pos] > curVal) {
                nums[pos+1] = nums[pos];
                pos--;
            }
            nums[pos+1] = curVal;
        }
        return nums;
    }
}
