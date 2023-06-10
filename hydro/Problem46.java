/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-06-10 13:13:55
 * @LastEditTime: 2023-06-10 13:34:29
 */
package hydro;

import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1089
 * 
 * 问题描述：给定一个字符串，要求将其修改为一个回文字符串，所修改的字符位置最多只能有两个，并且要求修改后的字符串字典序最小。
 * 
 * 输入描述：一行，一个字符串。字符串中仅由小写英文字符构成。保证字符串不会是空字符串。字符串长度介于[1,100000] 之间。
 * 
 * 输出描述：一行，一个在题目条件限制下所可以获得的字典序最小的回文字符串。
 * 
 */
public class Problem46 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int i = 0, j = str.length() - 1;
        char[] chs = str.toCharArray();
        int n = chs.length;
        int count = 0;
        while (i < j) {
            if (chs[i] != chs[j]) {
                // 删除了在这里直接进行修改的代码，这里仅仅是用来统计不同的对数
                count++;
            }
            i++;
            j--;
        }

        if (count == 2) {
            for (int k = 0; k < n / 2; k++) {
                if (chs[k] < chs[n - k - 1]) {
                    chs[n - k - 1] = chs[k];
                } else {
                    chs[k] = chs[n - k - 1];
                }
            }
            System.out.println(new String(chs));

        } else if (count == 1) {
            // 只有一对位置是不同的，问题的关键其实在于不同的这一对位置是否等于'a'
            for (int k = 0; k < n / 2; k++) {
                if (chs[k] != chs[n - k - 1]) {
                    if (chs[k] == 'a' || chs[n - k - 1] == 'a') {
                        count++;
                    }
                    chs[k] = 'a';
                    chs[n - k - 1] = 'a';
                    count--;
                }

                if (count > 0 && n % 2 != 0) {
                    chs[n / 2] = 'a';
                }
            }

            System.out.println(new String(chs));
        } else if (count == 0) {
            // 本身就是回文串，找到第一个不是a的地方修改了就好了
            int idx = 0;
            while (idx <= n / 2 && count < 2) {
                if (chs[idx] == 'a') {
                    idx++;
                } else {
                    chs[idx] = 'a';
                    chs[n - idx - 1] = 'a';
                    count += 2;
                }
            }

            System.out.println(new String(chs));
        }

        in.close();
    }

    /**
     * 我的代码，没有AC，25/30
     */
    public static void notAC(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int i = 0, j = str.length() - 1;
        char[] chs = str.toCharArray();
        int n = chs.length;
        int count = 0;
        while (i < j) {
            if (chs[i] != chs[j]) {
                if (chs[i] < chs[j]) {
                    chs[j] = chs[i];
                } else {
                    chs[i] = chs[j];
                }
                count++;
            }
            i++;
            j--;
        }

        if (count == 2) {
            System.out.println(new String(chs));
        } else if (count == 0) {
            // 还有操作次数
            // 从头开始找到最不是a的地方
            int idx = 0;
            while (idx <= n / 2 && count < 2) {
                if (chs[idx] == 'a') {
                    idx++;
                } else {
                    chs[idx] = 'a';
                    chs[n - idx - 1] = 'a';
                    count += 2;
                }
            }

            System.out.println(new String(chs));
        } else if (count == 1) {
            if (n % 2 != 0) {
                /**
                 * 这道题目错误的地方就在于这里，当count=1时，也就是说只有一对位置时不匹配的
                 * 因为我们上面在统计的时候就会把这对位置改了，比如abca --> count == 1 根据我们的代码会返回 abba
                 * 但实际上应该返回的是aaaa
                 * 
                 * 也就是说，我们并没有考虑到字符串是偶数的情况
                 */
                chs[n / 2] = 'a';

                /**
                 * 按照上面的说法，我们如果加上对于偶数情况的处理的话，如下代码
                 * else {
                 * int idx = 0;
                 * while (idx <= n / 2 && count < 2) {
                 * if (chs[idx] == 'a') {
                 * idx++;
                 * } else {
                 * chs[idx] = 'a';
                 * chs[n - idx - 1] = 'a';
                 * count += 2;
                 * }
                 * }
                 * }
                 * 这样还是不行，因为这相当于在修改1次后的回文串中找到第一个不是a的地方，
                 * 然后把这一对的字符在修改了，这相当于修改了3次，是不符合题意的
                 */
            }
            System.out.println(new String(chs));
        }
        in.close();
    }
}
