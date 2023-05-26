package hydro;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1040
 * 
 * 问题描述：塔子哥从老师那得到一个字符串，老师告诉他里面藏着一段数字密码，并且说数字密码是升序排放的。
 * 该字母是由数字密码中的每个数字转换成对应的英文（即1对应“one”，2对应“two”等等），
 * 然后将英文随机的转为大写并打乱得到的。塔子哥的CUP几乎都要烧坏了，他想问问你怎么解决这个问题？
 * 【注：只有0-9这些数字】
 * 
 * 输入描述：输入一行字符串，表示字母s,∣s∣≤100000
 * 
 * 输出描述：输出对应的数字密码
 * 
 * 这道题的思路是根据不同英文字母中存在的唯一特殊字符依次筛选
 * 比如six中有特殊的字符x，那么我们只需要看输入的字符串中有多少个x就能够知道有多少个6
 */
public class Problem38 {
    /**
     * zero
     * one
     * two
     * three
     * four
     * five
     * six
     * seven
     * eight g特殊
     * nine
     */
    static HashMap<Character, Integer> memo = new HashMap<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine().toLowerCase();

        for (char ch : str.toCharArray()) {
            memo.put(ch, memo.getOrDefault(ch, 0) + 1);
        }
        // 定义一下遍历的顺序
        int[] order = new int[] { 0, 6, 8, 3, 2, 4, 5, 1, 7, 9 };
        String[] enNums = new String[] { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight",
                "nine" };

        int[] ans = new int[10];
        for (int i = 0; i < order.length; i++) {
            ans[order[i]] = process(enNums[order[i]]);
        }

        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[i]; j++) {
                System.out.print(i);
            }
        }

        in.close();
    }

    private static int process(String str) {
        char[] chs = str.toCharArray();
        // 比如说传进来的是一个six，其实我就要看看这三个字符谁在哈希表中是最少的
        int minCount = Integer.MAX_VALUE;
        for (char ch : chs) {
            int count = memo.getOrDefault(ch, 0);
            minCount = Math.min(minCount, count);
        }

        if (minCount > 0) {
            for (char ch : chs) {
                int count = memo.get(ch);
                memo.put(ch, count - minCount);
            }
        }

        return minCount;
    }
}
