package backtracking.palindrome;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 131 middle 分割回文串
 * 
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * 回文串 是正着读和反着读都一样的字符串。
 * 
 */
public class SplitPalindrome {
    List<List<String>> ans = new ArrayList<>();

    public List<List<String>> partition(String s) {
        backtrack(s, 0, new ArrayList<>());
        return ans;
    }

    private boolean isPalindrome(String str) {
        int i = 0, j = str.length() - 1;
        while (i <= j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    // 字符串s从idx处开始分割
    private void backtrack(String s, int idx, List<String> path) {
        if(idx == s.length()) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = idx; i < s.length(); i++) {
            if (isPalindrome(s.substring(idx, i + 1))) {
                path.add(s.substring(idx, i + 1));
                backtrack(s, i + 1, path);
                path.remove(path.size() - 1);
            } else {
                continue;
            }
        }
    }
}
