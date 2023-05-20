/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-19 21:53:16
 * @LastEditTime: 2023-05-20 15:07:54
 */
package hydro;

import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1027
 * 
 * 问题描述：该算法的原理是，对于待加密的字符串中的每个字母，根据预先定义好的数组 a 中对应的元素，对其进行偏移。
 * 其中，数组 a 的前三位已经被设定为 a[0]=1,a[1]=2,a[2]=4，而当 i>=3 时，数组元素 a[i] 的值是 a[i-1]+a[i-2]+a[i-3]。
 * 在组织中，每个成员都知道完整的数组 a，因此可以使用该算法对通信内容进行加密。当成员 A 想要给成员 B 发送一条加密信息时，
 * 他会先将消息中的每个字母按照数组 a 中对应的元素进行偏移，从而得到加密后的消息。
 * 为了解密该信息，成员 B 需要知道完整的数组 a，并对每个字母根据对应的数组元素进行反向偏移。只有这样，他才能够正确地读取消息内容。
 * 现在塔子哥得到一个未加密的字符串，他想知道加密以后的字符串是啥，但是他不知道数组 a 是啥，请问身为组织成员的你能不能帮帮他？
 * 
 * 输入描述：输入一行未加密的字符串，字符串长度不会超过100000。
 * 
 * 输出描述：输出加密后的字符串
 */
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
