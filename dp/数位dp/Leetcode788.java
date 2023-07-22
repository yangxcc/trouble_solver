package dp.数位dp;

/**
 * leetcode 788 middle 旋转数字
 *
 * 我们称一个数 X 为好数, 如果它的每位数字逐个地被旋转 180 度后，我们仍可以得到一个有效的，且和 X 不同的数。要求每位数字都要被旋转。
 * 如果一个数的每位数字被旋转以后仍然还是一个数字， 则这个数是有效的。0, 1, 和 8 被旋转后仍然是它们自己；
 * 2 和 5 可以互相旋转成对方（在这种情况下，它们以不同的方向旋转，换句话说，2 和 5 互为镜像）；6 和 9 同理，除了这些以外其他的数字旋转以后都不再是有效的数字。
 *
 * 现在我们有一个正整数 N, 计算从 1 到 N 中有多少个数 X 是好数？
 */
public class Leetcode788 {
    char[] chs;
    static int[] DIFFS = new int[]{0, 0, 1, -1, -1, 1, 1, -1, 0, 1};
    public int rotatedDigits(int n) {
        this.chs = Integer.toString(n).toCharArray();

//        return dp(0, 0, true, false);
        return dp(0, false, true);
    }

    // cnt表示的是当前有多少个好数
    // 这种做法是不对的，因为根据好数的定义：好数中是存在[2,5,6,9]中至少一个元素的数
    // 我们下面的这种做法是分别算出含有2的有多少个数，含有4的有多少个数....
    // 但是这样造成了重复，因为含有2的可能也含有4....
    // 所以不能这么做
    private int dp(int i, int cnt, boolean limit, boolean isNum) {
        if (i == chs.length) {
            return cnt;
        }

        int ans = 0;
        if (!isNum) {
            ans = dp(i + 1, cnt, false, false);
        }

        int up = limit ? chs[i] - '0' : 9;
        int down = isNum ? 0 : 1;
        for (int d = down; d <= up; d++) {
            if (d == 2 || d == 5 || d == 6 || d == 9) {
                ans += dp(i + 1, cnt + 1, limit && d == up, true);
            } else {
                ans += dp(i + 1, cnt, limit && d == up, true);
            }
        }

        return ans;
    }

    // hasDiff表示的是当前构造的数中是否包含2,5,6,9
    private int dp(int i, boolean hasDiff, boolean limit) {
        if (i == chs.length) {
            return hasDiff ? 1 : 0;
        }

        int ans = 0;
        int up = limit ? chs[i] - '0' : 9;
        for (int d = 0; d <= up; d++) {
            if (DIFFS[d] != -1) {
                ans += dp(i + 1, hasDiff || DIFFS[d] == 1, limit && d == up);
            }
        }

        return ans;
    }

}
