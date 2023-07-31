package skill;

/**
 * 打靶问题：射击运动员打10枪，命中90环的可能次数
 */
public class ShootingProblem {
    public static void main(String[] args) {
//        System.out.println(numberOfWays(10, 90));
        backtrack(10, 90);
        System.out.println(ans);
    }


    /**
     *
     * @param shots 打靶次数
     * @param score 总环数
     */
    private static int numberOfWays(int shots, int score) {
        if (shots == 0 && score == 0) {
            return 1;
        }
        if (shots <= 0 || score < 0) {
            return 0;
        }
        int count = 0;
        for (int i = 10; i >= 0; i--) {
            count += numberOfWays(shots - 1, score - i);
        }
        return count;
    }

    static int ans = 0;
    // 从[0,10]这个区间内选择10个数，能够组成90的个数
    private static void backtrack(int shots, int score) {
        if (shots == 0 && score == 0) {
            ans++;
            return;
        }
        if (shots <= 0 || score < 0) {
            return;
        }

        for (int i = 0; i <= 10; i++) {
            shots--;
            score -= i;
            backtrack(shots, score);
            shots++;
            score += i;
        }
    }
}
