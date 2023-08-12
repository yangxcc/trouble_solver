/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-06 14:27:05
 * @LastEditTime: 2023-04-12 21:54:55
 */
package array.binary_search.application;

/**
 * leetcode 1011 middle 在 D 天内送达包裹的能力
 * 
 * 传送带上的包裹必须在 days 天内从一个港口运送到另一个港口。
 * 传送带上的第 i 个包裹的重量为 weights[i]。
 * 每一天，我们都会按给出重量（weights）的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
 * 返回能在 days 天内将传送带上的所有包裹送达的船的最低运载能力。
 */
public class Leetcode1011 {
    public int shipWithinDays(int[] weights, int days) {
        int minCapacity = 0, maxCapacity = 0;
        for (int weight : weights) {
            minCapacity = Math.max(minCapacity, weight); // 需要保证每天都能运送货物
            maxCapacity += weight;
        }

        // 找左边界
        while (minCapacity <= maxCapacity) {
            int mid = minCapacity + (maxCapacity - minCapacity) / 2;
            if (CountingDays(weights, mid) == days) {
                maxCapacity = mid - 1;
            } else if (CountingDays(weights, mid) < days) {
                // 这里一定要注意，别惯性思维，看起来像是 nums[mid] < target，就写成left=mid+1
                // 要考虑现实意义，计算出来的天数小于days，那么我们就要调大
                maxCapacity = mid - 1;
            } else if (CountingDays(weights, mid) > days) {
                minCapacity = mid + 1;
            }
        }

        // 根据题意，这里不需要额外进行判断了，因为target一定是会存在于数组中的

        return minCapacity;
    }

    public int CountingDays(int[] weights, int capacity) {
        int days = 0;
        for (int i = 0; i < weights.length;) {
            // 每天都尽可能地装货
            int tmpCapacity = capacity;
            while (i < weights.length) {
                tmpCapacity -= weights[i];
                if (tmpCapacity < 0) {
                    break;
                }
                i++;
            }
            days++;
        }

        return days;
    }
}