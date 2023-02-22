package string.reverse;

/**
 * leetcode 151 middle 反转字符串中的单词
 * 
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。
 * 返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 */
public class ReverseWords {
    // 调api杀手😂
    public String reverseWords(String s) {
        s = s.trim();
        String[] str = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = str.length - 1; i >= 0; i--) {
            sb.append(str[i]).append(' ');
        }
        return sb.toString().trim();
    }

    public String reverseWords2(String s) {
        StringBuilder sb = removeSpace(s); // 去掉前后的空格，且每个单词后面都有一个空格，最后一个单词那里没有

        // 先整体都翻转过来，再翻转每一个单词
        for (int i = 0, j = sb.length() - 1; i < j; i++, j--) {
            char tmp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(j));
            sb.setCharAt(j, tmp);
        }

        int start = 0, end = 0;
        while (end < sb.length()) {
            while (end < sb.length() && sb.charAt(end) != ' ') {
                end++;
            }

            // reverse from start to end
            for (int i = start, j = end - 1; i < j; i++, j--) {
                char tmp = sb.charAt(i);
                sb.setCharAt(i, sb.charAt(j));
                sb.setCharAt(j, tmp);
            }

            start = end + 1;
            end++;
        }

        return sb.toString();
    }

    private StringBuilder removeSpace(String s) {
        char[] ch = s.toCharArray();
        int left = 0, right = s.length() - 1;
        while (left <= right && ch[left] == ' ') {
            left++;
        }

        while (left <= right && ch[right] == ' ') {
            right--;
        }

        StringBuilder ans = new StringBuilder();

        while (left <= right) {
            // 重点在这个判断上，目的是让每个单词后面都跟上一个空格
            if (ch[left] != ' ' || ans.charAt(ans.length() - 1) != ' ') {
                ans.append(ch[left]);
            }
            left++;
        }

        return ans;
    }
}