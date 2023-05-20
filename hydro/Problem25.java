/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-19 21:53:16
 * @LastEditTime: 2023-05-20 15:04:29
 */
package hydro;

import java.util.Scanner;

public class Problem25 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int arrLen = Math.max(3, str.length());
        int[] arr = new int[arrLen];
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 4;
        for (int i = 3; i < arrLen; i++) {
            arr[i] = (arr[i - 1] + arr[i - 2] + arr[i - 3]) % 26;
        }

        StringBuilder ans = new StringBuilder();
        char[] chs = str.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            chs[i] += arr[i];
            if (chs[i] > 'z') {
                chs[i] -= 26;
            }

            ans.append(chs[i]);
        }
        System.out.println(ans.toString());
        in.close();
    }
}
