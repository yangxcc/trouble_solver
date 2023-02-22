/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-11-12 19:31:24
 * @LastEditTime: 2023-02-18 10:09:33
 */
package backtracking.other.restore_ip;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 剑指offerII-87/93 middle 复原IP
 * 
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址。
 * 你可以按任何顺序返回答案。
 * 
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，
 * 但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。 
 */
public class restoreIP {
    List<String> ans = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        if (s.length() < 4) {
            return new ArrayList<>();
        }
        backtrack(s, 0, 0);
        return ans;
    }

    // 直接在原串上进行操作，不要再弄stringbuilder了，AC不了，不知道哪里出现了问题
    private void backtrack(String s, int idx, int dotCount) {
        if (dotCount == 3) {
            if (isValid(s.substring(idx))) {
                ans.add(s);
            }
            return;
        }

        for (int i = idx; i < s.length(); i++) {
            if (isValid(s.substring(idx, i + 1))) {
                // 在i后面插入一个点
                s = s.substring(0, i + 1) + '.' + s.substring(i + 1);
                dotCount++;

                // 注意这里要把那个点越过去
                backtrack(s, i + 2, dotCount);

                s = s.substring(0, i + 1) + s.substring(i + 2);
                dotCount--;
            }
        }
    }

    // str的数是否在[0, 255]，且不含有前导0
    private boolean isValid(String str) {
        if (str.length() < 1 || str.length() > 3) {
            return false;
        }

        if (str.charAt(0) == '0') {
            return str.length() == 1;
        }

        int num = Integer.parseInt(str);

        return num >= 0 && num <= 255;
    }
}
