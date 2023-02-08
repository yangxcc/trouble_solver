package random;

import java.util.Arrays;
import java.util.Random;

/**
 * 洗牌算法，用来打乱数组
 * 
 * 例题：leetcode 384 middle 打乱数组
 */
public class Shuffle {
    private int[] nums;

    public Shuffle(int[] nums) {
        this.nums = nums;
    }

    public int[] shuffle() {
        int n = nums.length;
        int[] copy = Arrays.copyOf(nums, n);
        for (int i = 0; i < n; i++) {
            // 生成一个[i, n)的随机数
            int randIdx = i + new Random().nextInt(n - i);
            swap(copy, i, randIdx);
        }

        return copy;
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
