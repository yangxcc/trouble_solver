/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-12 21:30:30
 * @LastEditTime: 2023-04-15 13:44:17
 */
package array.diff_array.application;

/**
 * leetcode 1109 middle 航班预定统计
 * 
 * 这里有 n 个航班，它们分别从 1 到 n 进行编号。
 * 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi]
 * 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
 * 
 * 请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。
 */
public class FlightBooking {

    public int[] corpFlightBookings(int[][] bookings, int n) {
        Diff d = new Diff(n);

        for (int[] interval : bookings) {
                int left = interval[0] - 1;
                int right = interval[1] - 1;
                int val = interval[2];
                d.update(left, right, val);
        }
        return d.recover();
    }

}

class Diff {
    private int[] diff;

    public Diff(int n) {
        this.diff = new int[n];
    }

    public void update(int left, int right, int val) {
        diff[left] += val;
        if (right + 1 < diff.length) {
            diff[right + 1] -= val;
        }
    }

    public int[] recover() {
        int[] ans = new int[diff.length];
        ans[0] = diff[0];
        for (int i = 1; i < diff.length; i++) {
            ans[i] = diff[i] + ans[i - 1];
        }
        return ans;
    }
}

/**
 * 这里有 n 个航班，它们分别从 1 到 n 进行编号。
 * 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 
 * 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
 * 请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。
 */
class day0415 {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        Difference diff = new Difference(n);
        for (int[] book : bookings) {
            diff.update(book[0], book[1], book[2]);
        }

        return diff.recover();
    }

    class Difference {
        int[] diff;
        
        public Difference(int n) {
            this.diff = new int[n];
        }

        public void update(int left, int right, int val) {
            diff[left] += val;
            if (right + 1 < diff.length) {
                diff[right + 1] -= val;
            }
        }

        public int[] recover() {
            int[] ans = new int[diff.length];
            ans[0] = diff[0];
            for (int i = 1; i < diff.length; i++) {
                ans[i] = ans[i - 1] + diff[i];
            }

            return ans;
        }
    }
}