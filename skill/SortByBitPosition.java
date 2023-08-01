package skill;

import java.util.Arrays;

/**
 * 根据数位排序，比如17和2，因为2比1大，所以2放在后面
 * 题目：3 30 34 5 9，找该排列组合成一个数的最大值（9534330）
 */
public class SortByBitPosition {
    public static String process(String[] nums) {
        Arrays.sort(nums, (a, b) -> {
            return -Integer.parseInt(a + b) + Integer.parseInt(b + a);
        });
        StringBuilder ans = new StringBuilder();
        for (String num : nums) {
            ans.append(num);
        }

        return ans.toString();
    }

    public static void main(String[] args) {
        String[] nums = {"3", "30", "34", "5", "9"};
        System.out.println(process(nums));
    }
}
