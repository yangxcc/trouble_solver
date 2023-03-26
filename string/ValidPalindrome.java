package string;

/**
 * leetcode 125 simple 验证回文串
 * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。
 * 则可以认为该短语是一个 回文串 。
 * 
 * 字母和数字都属于字母数字字符。
 * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false
 */
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        char[] chs = s.toLowerCase().toCharArray();
        int left = 0, right = chs.length - 1; 

        while (left < right) {
            if (Character.isLetterOrDigit(chs[left]) && Character.isLetterOrDigit(chs[right]) && chs[left] != chs[right]) {
                return false;
            } else if (!Character.isLetterOrDigit(chs[left])) {
                left++;
            } else if (!Character.isLetterOrDigit(chs[right])) {
                right--;
            } else {    
                left++;
                right--;
            }
        }
        
        return true;
    }
}
