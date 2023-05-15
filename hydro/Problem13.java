package hydro;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;;

/**
 * 题目来源：http://101.43.147.120/p/P1014
 * 
 * 题目描述：塔子最近在玩一款闯关游戏,其共有 m 个回合，塔子有个技能：塔子起飞。总共可以使用 x 次， 以下是对这个技能的简介:
 * 1.每回合使用不超过一次。
 * 2.如果在某一个回合使用了塔子起飞，那么塔子免疫该回合受到的所有伤害，并且在之后没有使用该技能的回合中每回合恢复 1 点体力值
 * 3.技能的效果可以叠加
 * 以下是对塔子起飞技能的案例说明:
 * 前两个回合都释放了一次塔子起飞，那么第三个回合就会恢复 2 点体力。
 * 通关条件:
 * 塔子只需要在 m 个回合后体力大于等于 0 ，即可通关。
 * 
 * 问题定义:请问塔子初始最少需要多少体力。(初始体力应该大于等于 1 , 过程中允许部分时刻体力小于 0 )
 * 
 * 输入描述：输入第一行包含两个整数 m和x，分别表示回合数和塔子起飞使用次数。(1≤m,x≤10^5)
 * 输入第二行包含 m 个整数，分别表示在第 1 个回合到第 m 个回合中，塔子受到伤害，每回合造成的伤害不超过 10^5
 * 
 * 输出描述：输出仅包含一个整数，即最少需要的体力值。
 */
public class Problem13 {
    /**
     * 这个没做出来，我直接使用dp了，但是后面做不下去了，因为他这个里面能够在部分时刻体力小于0，没法操作
     * 
     * 题解：
     * 如果在第i个回合使用技能，那么tazi的收益是多少？
     * 首先是能够躲过这一回合的进攻，记作v_i
     * 第二，在后续的n-i个回合中，最多能够获得n-i的体力
     * 第三，因为在这一回合使用技能之前，前面的回合使用过技能了，所以这一回合对于前面技能的加成是获取不到的，
     * 假设，前面使用了cnt次技能，也就是在这一回合会损失cnt的体力加成
     * 
     * 综上，如果在第i回合使用技能，那么tazi的总收益是 v_i + (n - i) - cnt
     * 
     * 最后计算时将 cnt 提取出来得到一个 0+1+2+...+x−1 的额外贡献，
     * 这部分贡献只跟使用的塔子起飞次数有关，所以不需要考虑，
     * n 为常数也不需要考虑，
     * 所以第 i 回合使用塔子起飞的贡献就可以看作v_i - i，根据这个值排序，并对贡献最大的前 x 个回合使用塔子起飞，
     * 最后模拟一遍即可。
     * 
     * 
     * 也就是说，这道题其实是贪心的思路
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt(), x = in.nextInt();
        in.nextLine();
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = in.nextInt();
        }

        List<int[]> container = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            // 存入v_i和i
            container.add(new int[] { arr[i], i });
        }
        
        Collections.sort(container, (a, b) -> {
            // 收益最高的放在前面，也就是在这个位置起飞
            return Integer.compare(b[0] - b[1], a[0] - a[1]);
        });

        // 将排名靠前的x个标记为是起飞的
        for (int i = 0; i < x; i++) {
            arr[container.get(i)[1]] = -1;
        }

        int ans = 0, cnt = 0;
        for (int i = 0; i < m; i++) {
            if (arr[i] == -1) {
                // 是起飞的
                cnt++;
            } else {
                ans += arr[i] - cnt;
            }
        }

        System.out.println(ans <= 1 ? 1 : ans);
    }
}
