package string.replace;

/**
 * leetcode 剑指offer-05 simple 替换空格
 * 
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"
 * 
 */
public class ReplaceSpace {
    public String replaceSpace(String s) {
        char[] chs = s.toCharArray();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == ' ') {
                ans.append("%20");
            } else {
                ans.append(chs[i]);
            }
        }

        return new String(chs);
    }
}
