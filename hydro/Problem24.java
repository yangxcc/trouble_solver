package hydro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;

/**
 * 问题来源：https://codefun2000.com/p/P1026
 * 问题描述：塔子哥开发了一套检测热点字符的工具。已知用户端读入的文本只由大写英文字母(A−Z )、小写英文字母(a−z )、
 * 数字(0−9 )三种字符组成。塔子哥为了持续检测其中的热点字符，需要每隔一段字符长度就进行 一次统计(假设统计单位为15 ,
 * 那么每接收15 个字符，就要对字符串的热点字符进行统计一次)。最后将每次的统计结果按次数从高到低排列。对于出现次数相同字符则按 ASCII 码值从大到小排列。
 * 
 * 输入描述：输入为一行:a b c，a 代表依次统计出现最多的a个字符，b 代表间隔长度. c 代表需要统计的字符串.
 * 输入字符串最大长度为 2000000
 * 
 * 输出描述：输出统计的结果的字符串。
 * 
 * 样例：输入 2 4 aabbbbaaaaaa，输出babaab
 * 样例解释：
 * 第一次统计下标是[0,3] 的字符串aabb , 发现a 出现两次,b 出现两次 ， b>a , 所以 ba
 * 第二次统计下标是[0,7] 的字符串aabbbbaa , 发现a 出现4次, b 出现4次 ，b>a , 所以 ba
 * 第三次统计下标是[0,11] 的字符串aabbbbaaaaaa , 发现a 出现8次,b 出现4次 ， ab
 * 
 * 
 * 通过这道题目，学会了treemap的使用，以及按照value值对treemap/hashmap进行排序
 */
public class Problem24 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] inputs = in.nextLine().split(" ");
        int a = Integer.parseInt(inputs[0]);
        int b = Integer.parseInt(inputs[1]);
        String c = inputs[2];

        StringBuilder ans = new StringBuilder();
        Map<Character, Integer> char2Count = new TreeMap<>();
        for (int i = 0; i < c.length(); i += b) {
            int count = 0;
            while (count < b) {
                char2Count.put(c.charAt(i + count), char2Count.getOrDefault(c.charAt(i + count), 0) + 1);
                count++;
            }

            List<Map.Entry<Character, Integer>> tmpList = new ArrayList<>(char2Count.entrySet());
            Collections.sort(tmpList, new Comparator<Map.Entry<Character, Integer>>() {
                @Override
                public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                    if (o1.getValue().equals(o2.getValue())) {
                        return o2.getKey().compareTo(o1.getKey());
                    }

                    return o2.getValue().compareTo(o1.getValue());
                }
            });

            if (a <= tmpList.size()) {
                for (int j = 0; j < a; j++) {
                    ans.append(tmpList.get(j).getKey());
                }
            } else {
                for (int j = 0; j < a; j++) {
                    ans.append(tmpList.get(0).getKey());
                }
            }
        }

        System.out.println(ans.toString());
        in.close();
    }
}
