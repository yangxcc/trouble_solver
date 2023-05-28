package hydro;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1051
 * 
 * 问题描述：罗马数字转整数，整数转罗马数字
 * （分别对应了Leetcode12和Leetcode13）
 * 
 * 输入描述：一行十进制整数(1≤n≤1000)
 * 
 * 输出描述：一行字符串，表示对应的罗马数字
 * 
 * 一定要记住，整形转罗马时要把900， 400这些给存起来，罗马转整形只需要存7个基本的就好了
 * 
 */
public class Problem41 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        System.out.println(int2Roman(num));
        in.nextLine();
        String roman = in.nextLine();
        System.out.println(roman2Int(roman));
        in.close();
    }

    private static String int2Roman(int num) {
        int[] values = new int[] { 1000, 900, 500, 400, 100, 90, 10, 9, 5, 4, 1 };
        String[] symbols = new String[] { "M", "CM", "D", "CD", "C", "XC", "X", "IX", "V", "IV", "I" };

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            int val = values[i];
            String symbol = symbols[i];

            while (num >= val) {
                ans.append(symbol);
                num -= val;
            }

            if (num == 0) {
                break;
            }
        }

        return ans.toString();
    }

    private static int roman2Int(String roman) {
        HashMap<Character, Integer> memo = new HashMap<>();
        memo.put('I', 1);
        memo.put('V', 5);
        memo.put('X', 10);
        memo.put('L', 50);
        memo.put('C', 100);
        memo.put('D', 500);
        memo.put('M', 1000);

        int ans = 0;
        char[] chs = roman.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            if (i + 1 < chs.length && chs[i] < chs[i + 1]) {
                ans -= memo.get(chs[i]);
            } else {
                ans += memo.get(chs[i]);
            }
        }

        return ans;
    }
}
