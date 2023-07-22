package dp.数位dp;

public class Variant {
    // 字节面试题：给一个数n，一个数组A，返回由A中元素组成的小于n的最大数
    /**
     * 如n=23121，A={2,4,9} 返回22999
     * n=23121 A={9} 返回9999
     * n=23333 A={2,3} 返回23332
     * n=2222 A={2} 返回222
     * n=2 A={2} 无解
     */
    public static void main(String[] args) {
        int n = 23121;
        int[] diff = {2, 4, 9};
        process(n - 1, diff);
        System.out.println(res);
    }

    static char[] chs;
    static int res = 0;
    static int[] diff;  // static是this天生不能一起用，static是类的变量/方法，this是对象的变量/方法

    public static void process(int n, int[] _diff) {
        diff = _diff;
        chs = Integer.toString(n).toCharArray();

        dp(0, 0, true, false);
    }
    private static void dp(int i, int curVal, boolean limit, boolean isNum) {
        if (i == chs.length) {
            res = Math.max(res, curVal);
            return;
        }

        if (!isNum) {
            dp(i + 1, curVal, false, false);
        }

        int up = limit ? chs[i] - '0' : 9;
        int down = isNum ? 0 : 1;
        for (int d : diff) {
            if (d > up || d < down) {
                continue;
            }
            dp(i + 1, curVal * 10 + d, limit && d == up, true);
        }
    }
}
