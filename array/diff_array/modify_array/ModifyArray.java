/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-12 21:04:58
 * @LastEditTime: 2022-12-12 21:27:03
 */
package array.diff_array.modify_array;

public class ModifyArray {

    public int[] getModifiedArray(int length, int[][] updates) {
        Difference d = new Difference(length);
        for (int[] updateVals : updates) {
            d.update(updateVals[0], updateVals[1], updateVals[2]);
        }
        return d.recover();
    }
    
}

// 差分数组工具类
class Difference{
    private int[] diff;

    public Difference(int n) {
        diff = new int[n];
    }

    public void update(int left, int right, int val) {
        diff[left] += val;
        if (right + 1 < diff.length) {
            diff[right+1] -= val;
        }
    }

    public int[] recover() {
        int[] ans = new int[this.diff.length];
        ans[0] = this.diff[0];

        for (int i = 1; i < ans.length; i++) {
            ans[i] = this.diff[i] + ans[i-1];
        }

        return ans;
    }
}
