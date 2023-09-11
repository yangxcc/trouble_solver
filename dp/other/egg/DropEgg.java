package dp.other.egg;

/**
 * leetcode 887 hard 鸡蛋掉落
 * 
 * 给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。
 * 已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。
 * 每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。
 * 如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。
 * 
 * 请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
 */
public class DropEgg {
    public int superEggDrop(int k, int n) {
        memo = new int[k + 1][n + 1];

        return dp(k, n);
    }

    int[][] memo;
    // dp表示的是用k个鸡蛋检验n层楼在最坏情况下的最少交易次数
    public int dp(int k, int n) {
        if (k == 1) {
            return n;
        }

        if (n == 0) {
            return 0;
        }

        if (memo[k][n] != 0) {
            return memo[k][n];
        }

        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            //在第i层楼上碎了，在第i层楼上没碎  max表示的是最坏情况
            res = Math.min(res, Math.max(dp(k - 1, i - 1), dp(k, n - i)) + 1);
        }

        memo[k][n] = res;

        // 使用二分提高效率
        // int left = 1;
        // int right = n;
        // while(left <= right) {
        //     int mid = (left + right) / 2;
        //     int broken = dp(k - 1, mid - 1);
        //     int not_broken = dp(k, n - mid);
        //     if (broken > not_broken) {
        //         res = Math.min(res, broken + 1);
        //         right = mid - 1;
        //     } else {
        //         res = Math.min(res, not_broken + 1);
        //         left = mid + 1;
        //     }
        // }
        // memo[k][n] = res;
        // return res;

        return res;
    }
}
