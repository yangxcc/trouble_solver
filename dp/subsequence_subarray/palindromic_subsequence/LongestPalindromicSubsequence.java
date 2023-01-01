/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-10 15:57:50
 * @LastEditTime: 2023-01-01 16:44:39
 */
package subsequence_subarray.palindromic_subsequence;

public class LongestPalindromicSubsequence {
    public String longestPalindrome(String s) {
        String ans = "";
        for (int i = 0; i < s.length(); i++) {
            String s1 = findPalindrome(s, i, i);     // 以(i,i)为中心
            String s2 = findPalindrome(s, i, i+1);   // 以(i,i+1)位为中心

            ans = s1.length() > ans.length() ? s1 : ans;
            ans = s2.length() > ans.length() ? s2 : ans;
        }
        return ans;
    }

    public String findPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return s.substring(left+1, right);
    }
}
