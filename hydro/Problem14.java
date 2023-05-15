package hydro;

import java.util.Scanner;

/**
 * 题目来源：
 * 题目描述：塔子哥在玩一款弱智游戏，他的第一关中有 n 只怪兽，塔子哥的目标就是杀死这 n个怪兽。现在已知每只怪兽的血量为hp_i ，
 * 塔子哥的攻击力为g，每回合塔子哥首先使用AOE技能，对所有怪兽造成 g 点伤害，紧接着所有存活的怪物同时攻击塔子哥，造成x点伤害(x为当前存活怪兽数量)
 * 塔子哥想知道，自己击杀完所有怪兽将损失多少血量?
 * 
 * 输入描述：第一行输入两个正整数n和g，分别代表怪兽的数量和塔子哥的攻击力。
 * 第二行输入n和正整数hp_i，代表每个怪兽的血量
 * 1≤n≤10，1≤g,hp_i≤10^9
 * 输出描述：一个整数，代表塔子哥损失的血量。
 */
public class Problem14 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), g = in.nextInt();
        int[] arr = new int[n];
        in.nextLine();
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int ans = 0;
        while (true) {
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (arr[i] <= g) {
                    cnt++;
                    continue;
                } else {
                    arr[i] -= g;
                    ans++;
                }
            }
            if (cnt == n) {
                break;
            }
        }

        System.out.println(ans);
    }
}
