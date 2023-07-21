/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-13 17:37:12
 * @LastEditTime: 2022-12-13 17:41:37
 */
package sort;

public class utils {
    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
