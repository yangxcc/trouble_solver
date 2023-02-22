package string.kmp;

public class KMP {
    // next[i]表示的是[0..i]这段字符串的最长公共的前缀后缀
    // 前缀：以首位开头，不包含末尾的所有子字符串
    // 后缀：以末尾结尾，不包含首位的所有子字符串
    private int[] next;
    private String pattern; 

    public KMP(String pattern) {
        this.pattern = pattern;
        // dp数组只和pattern有关系
        this.next = new int[pattern.length()];
        int j = 0;  // 用于指向前缀的末尾
        next[0] = j;
        for (int i = 1; i < pattern.length(); i++) {
            // 注意看，这里的i有了一个特殊的含义，它指向的是后缀的末尾
            while (j > 0 && pattern.charAt(j) != pattern.charAt(i)) {
                // 后缀和前缀的字符不相等了，就往前看一下，这里注意next数组的意义，最长公共前后缀
                j = next[j - 1];
            }

            if (pattern.charAt(j) == pattern.charAt(i)) {
                j++;
            }

            next[i] = j;
        }
    }

    // 借助dp数组，找到pattern在text中首次出现的位置
    public int search(String text) {
        int j = 0;
        for (int i = 0; i < text.length(); i++) {
            while (j > 0 && pattern.charAt(j) != text.charAt(i)) {
                j = next[j - 1];
            }
            if (pattern.charAt(j) == text.charAt(i)) {
                j++;
            }

            if (j == pattern.length()) {
                return i - pattern.length() + 1;
            }
        }

        return -1;
    }
}
