/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-11-22 16:36:07
 * @LastEditTime: 2023-02-22 11:34:40
 */
package greedy.monotone_increasing_digits;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * leetcode 738 middle 单调递增的数字
 * 
 * 当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。
 * 给定一个整数 n ，返回 小于或等于 n 的最大数字，且数字呈 单调递增 。
 */
public class MonotoneIncreaseDigits {
    public int monotoneIncreasingDigits(int n) {
        if (n < 10) {
            return n;
        }
        
        // 从后往前，需要两个不符合递增规则的数之后，前一个字符-1，后面所有的字符都变9
        LinkedList<Integer> digits = new LinkedList<>();
        while (n > 0) {
            digits.addFirst(n % 10);
            n /= 10;
        }

        for (int i = digits.size() - 1; i > 0; i--) {
            if (digits.get(i) < digits.get(i - 1)) {
                digits.set(i - 1, digits.get(i - 1) - 1);
                for (int j = i; j < digits.size(); j++) {
                    digits.set(j, 9);
                }
            }
        }
        int ans = 0;
        for (int ele : digits) {
            ans = ans * 10 + ele;
        }
        return ans;
    }
}
