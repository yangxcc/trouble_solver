/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-06-14 21:59:05
 * @LastEditTime: 2023-06-14 22:11:56
 */
package hydro;

import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1238
 * 
 * 问题描述：塔子哥是一个收藏家，他喜欢收集各种珍奇的物品。最近，他在旅游时在俄罗斯购买了一些俄罗斯套娃。
 * 他深深地被这些绚丽多彩的小玩意吸引住了，每天都会花费大量的时间玩弄它们。
 * 随着时间的推移，他逐渐发现将套娃放在其他套娃内是一个有趣的游戏，并决定挑战自己，看看他是否能以最小的成本套上所有的俄罗斯套娃。
 * 具体的，塔子哥有 n 个俄罗斯套娃，第 i 个俄罗斯套娃的大小为ai，内部空间为bi(bi≤ai)和一个价值 ci。
 * 对于两个俄罗斯套娃x和y ，x 能放入y中当且仅当 ax≤by，且放入后会占据 y 大小为 ax 的内部空间，即y 的内部空间剩下by−ax，
 * 每个俄罗斯套娃只能放在另外的一个俄罗斯套娃内，每个俄罗斯套娃内部也只能放一个俄罗斯套娃(当然内部放的这个俄罗斯套娃可以内部还有俄罗斯套娃)。
 * 显然俄罗斯套娃是套的越多越好，如果套完之后俄罗斯套娃 i 还剩 k 的内部空间塔子哥需要付出 ci×k 的花费，总花费为所有俄罗斯套娃的花费之和。
 * 现在塔子哥想知道最小的花费为多少？
 * 
 * 输入描述：第一行一个正整数 n ，表示俄罗斯套娃的个数
 * 接下来三行每行 n 个整数，分别为
 * a1,a2,...,an
 * b1,b2,...,bn
 * c1,c2,...,cn
 * 1≤n,ai,bi,ci≤100000，bi≤ai
 * 
 * 输出描述：输出一个整数表示最小的花费。
 * 
 */
public class Probelm57 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] size = new int[n];
        int[] inner = new int[n];
        int[] cost = new int[n];
        for (int i = 0; i < n; i++) {
            size[i] = in.nextInt();
        }

        for (int i = 0; i < n; i++) {
            inner[i] = in.nextInt();
        }

        for (int i = 0; i < n; i++) {
            cost[i] = in.nextInt();
        }
        in.close();
    }
}
