package skill.factorial;

/**
 * leetcode 793 hard 阶乘函数后k个零
 * 
 * f(x) 是 x! 末尾是 0 的数量。回想一下 x! = 1 * 2 * 3 * ... * x，且 0! = 1 。
 * 例如， f(3) = 0 ，因为 3! = 6 的末尾没有 0 ；而 f(11) = 2 ，因为 11!= 39916800 末端有 2 个 0 。
 * 给定 k，找出返回能满足 f(x) = k 的非负整数 x 的数量。
 */
public class Leetcode793 {
    public int preimageSizeFZF(int k) {
        // 根据题意，k的取值范围是 0 <= k <= 10^9
        // 当n=Integer.MAX_VALUE时，k的取值都到不了10^9，因此这里取n=Long.MAX_VALUE;
        return (int) (rightBound(k) - leftBound(k) + 1);
    }

    public long leftBound(int k) {
        long left = 0;
        long right = Long.MAX_VALUE;

        while (left < right) {
            long mid = left + (right - left) / 2;
            if (count0(mid) == k) {
                right = mid;
            } else if (count0(mid) < k) {
                left = mid + 1;
            } else if (count0(mid) > k) {
                right = mid;
            }
        }

        return left;
    }

    public long rightBound(int k) {
        long left = 0;
        long right = Long.MAX_VALUE;

        while (left < right) {
            long mid = left + (right - left) / 2;
            if (count0(mid) == k) {
                left = mid + 1;
            } else if (count0(mid) < k) {
                left = mid + 1;
            } else if (count0(mid) > k) {
                right = mid;
            }
        }

        return right - 1;
    }

    /**
     * 统计x的阶乘末尾有多少个0
     * 
     * @param x
     * @return
     */
    public long count0(long x) {
        long ans = 0;
        long flag = 5;
        while (x >= flag) {
            ans += x / flag;
            flag *= 5;
        }

        return ans;
    }
}
