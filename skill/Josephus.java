package skill;

import java.util.ArrayList;
import java.util.List;

/**
 * 约瑟夫环问题
 * leetcode 剑指offer 62 圆圈中最后剩下的数字
 * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 */
public class Josephus {
    public int lastRemaining(int n, int m) {
        List<Integer> cycle = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cycle.add(i);
        }

        int idx = 0;
        while (cycle.size() > 1) {
            idx = (idx + m - 1) % cycle.size();
            cycle.remove(idx);
        }

        return cycle.get(0);
    }

    // 使用数学方法解决约瑟夫环问题：https://zhuanlan.zhihu.com/p/476678261
    public int lastRemaining2(int n, int m) {
        int ans = 0;
        for (int i = 2; i <= n; i++) {
            ans = (ans + m) % i;
        }
        return ans;
    }
}
