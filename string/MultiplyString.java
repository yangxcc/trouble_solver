package string;

/**
 * leetcode 43 middle 字符串相乘
 * 
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 */
public class MultiplyString {
    public String mulitply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        // 使用数组索引的位置来表示不同位置两个数的相乘结果
        int[] ans = new int[m + n];

        int multi = 0;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                multi = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                // i位置和j位置上相乘的数放到数组的i+j+1位置（+1单纯的是因为数组的长度为m+n）
                multi += ans[i + j + 1];
                ans[i + j + 1] = multi % 10;
                ans[i + j] += multi / 10;
            }
        }

        // 需要去掉前导0
        int idx = 0;
        for (int i = 0; i < m + n; i++) {
            if (ans[i] == 0) {
                idx++;
            } else {
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = idx; i < m + n; i++) {
            sb.append(ans[i]);
        }

        return sb.toString();
    }
}
