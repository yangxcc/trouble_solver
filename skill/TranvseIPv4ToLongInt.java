package skill;

import java.util.*;

/**
 * 华为笔试：IPv4地址转换成整数（面腾讯的时候没有写出来）
 * 存在一种虚拟地址IPv4地址，由四小节组成，每节的范围0~255，以#间隔，虚拟IPv4地址可以转换成为一个32位的整数
 * 例如：128#0#255#255，转换为32位整数的结果为2147549183
 */
public class TranvseIPv4ToLongInt {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        in.close();

        System.out.println(process1(input));
    }

    public static long process1(String input) {
        // 因为.是转义字符，所以需要使用\\. 不能使用.
        String[] strs = input.split("\\.");
        System.out.println(Arrays.toString(strs));
        long sum = 0l;
        for (int i = 0; i < strs.length; i++) {
            // ip地址实际上是256进制下的四个数字
            // sum = sum * 256 + Long.parseLong(strs[i]);
            sum += Long.parseLong(strs[i]) * Math.pow(256, 3 - i);
        }

        return sum;
    }
}
