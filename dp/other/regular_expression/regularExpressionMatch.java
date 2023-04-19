package other.regular_expression;

import java.util.Scanner;

/**
 * leetcode 10 hard 正则表达式匹配（剑指offer 10）
 * 
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *  '.' 匹配任意单个字符
 *  '*' 匹配零个或多个前面的那一个元素
 *  所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * 
 */
public class RegularExpressionMatch{
    public boolean isMatch(String s, String p) {
        return dp(s, 0, p, 0);
    }

    public boolean dp(String s, int i, String p, int j) {
        if (j == p.length()) {
            return i == s.length(); // j到头了，i是否也到头了
        }

        if (i == s.length()) {
            // 后面是 x*x*x* 这种模式的能够返回true
            int rest = p.length() - j;
            if (rest % 2 != 0) {
                return false;
            }
            for (int k = j + 1; k < p.length(); k += 2) {
                if (p.charAt(k) != '*') {
                    return false;
                }
            }
            return true;
        }

        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
            // 这里需要注意 . 后面是一个 * 的情况 s=aaa, p=.*
            // 这里很容易拉下
            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                // 分别表示重复n次和重复0次
                return dp(s, i + 1, p, j) || dp(s, i, p, j + 2);
            }
            return dp(s, i + 1, p, j + 1);
        } else {
            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                return dp(s, i, p, j + 2);
            }
            return false;
        }
    }
}

class day0419 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String p = in.nextLine();

        System.out.println(isMatch(s, p));
    }

    // 注意.能够匹配任意的单个字符，*能够匹配0个或多个前面的那一个元素
    private static boolean isMatch(String s, String p) {
        return dp(s, 0, p, 0);
    }

    private static boolean dp(String s, int i, String p, int j) {
        if (j == p.length()) {
            return i == s.length();
        }

        if (i == s.length()) {
            // ...x*x*x*x*x*
            int rest = p.length() - j;
            if (rest % 2 != 0) {
                return false;
            }

            for (int k = j + 1; k < p.length(); k += 2) {
                if (p.charAt(k) != '*') {
                    return false;
                }
            }

            return true;
        }

        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
            // 有可能是.*
            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                // 重复多次 || 重复0次
                return dp(s, i + 1, p, j) || dp(s, i, p, j + 2);
            }
            return dp(s, i + 1, p, j + 1);
        } else {
            // 是不是.

            // 是不是*，想让前面的字母重复
            // 是不是x*这种
            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                // 匹配0次
                return dp(s, i, p, j + 2);
            }

            return false;
        }
    }
}