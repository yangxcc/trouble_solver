package string.rotate;

public class RotateWords {
    public String reverseLeftWords(String s, int n) {
        String x = s + s;
        return x.substring(n, n + s.length());
    }

    // 先反转局部，再反转整体
    public String reverseLeftWords2(String s, int n) {
        char[] chs = s.toCharArray();
        reverse(chs, 0, n - 1);
        reverse(chs, n, chs.length - 1);
        reverse(chs, 0, chs.length - 1);

        return new String(chs);
    }

    public void reverse(char[] chs, int left, int right) {
        char tmp = chs[left];
        chs[left] = chs[right];
        chs[right] = tmp;
    }
}