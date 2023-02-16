package skill.collect_rain;

/**
 * 接雨水问题改编2，字节面试原题
 * 柱子没有宽度，求的是这些柱子最多能够存多少水
 * ContainerWithMostWater中问的是两个柱子之间最多能装多少水
 */
public class TrappingRainWater3 {
    public static int process(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int highestIdx = 0;
        int ans = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[highestIdx]) {
                ans += nums[highestIdx] * (i - highestIdx);
                highestIdx = i;
            }
        }

        highestIdx = nums.length - 1;
        for (int j = nums.length - 2; j >= 0; j--) {
            if (nums[j] > nums[highestIdx]) {
                ans += nums[highestIdx] * (highestIdx - j);
                highestIdx = j;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = { 3, 2, 5, 4, 6, 2 };
        System.out.println(process(nums));
    }
}
