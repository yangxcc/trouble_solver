package skill;

public class RMBConvert {
    // 整数部分的人民币大写
    private static final String[] NUMBERS = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
    // 数位部分
    private static final String[] IUNIT = { "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿" };
    // 小数部分的人民币大写
    private static final String[] DUNIT = { "角", "分", "厘" };

    // 转成中文的大写金额
    public static String toChinese(String str) {
        // 判断输入的金额字符串是否符合要求
        if (!str.matches("(-)?[\\d]*(.)?[\\d]*")) {
            System.out.println("抱歉，请输入数字！");
            return str;
        }
        // 判断输入的金额字符串
        if ("0".equals(str) || "0.00".equals(str) || "0.0".equals(str)) {
            return "零元";
        }

        // 判断是否存在负号"-"
        boolean flag = false;
        if (str.startsWith("-")) {
            flag = true;
            str = str.replaceAll("-", "");
        }

        String integerStr;// 整数部分数字
        String decimalStr;// 小数部分数字

        // 分离整数部分和小数部分
        if (str.indexOf(".") > 0) {// 整数部分和小数部分
            integerStr = str.substring(0, str.indexOf("."));
            decimalStr = str.substring(str.indexOf(".") + 1);
        } else if (str.indexOf(".") == 0) {// 只存在小数部分 .34
            integerStr = "";
            decimalStr = str.substring(1);
        } else { // 只存在整数部分 34
            integerStr = str;
            decimalStr = "";
        }

        // 整数部分超出计算能力，直接返回
        if (integerStr.length() > IUNIT.length) {
            System.out.println(str + "：超出计算能力");
            return str;
        }

        // 整数部分存入数组 目的是为了可以动态的在字符串数组中取对应的值
        int[] integers = toIntArray(integerStr);

        // 判断整数部分是否存在输入012的情况
        if (integers.length > 1 && integers[0] == 0) {
            System.out.println("抱歉，请输入数字！");
            if (flag) {
                str = "-" + str;
            }
            return str;
        }
        // boolean isWan = isWanUnits(integerStr);// 设置万单位
        // boolean isWan = false;

        // 小数部分数字存入数组
        int[] decimals = toIntArray(decimalStr);

        String result = getChineseInteger(integers) + getChineseDecimal(decimals);// 返回最终的大写金额

        if (flag) {
            return "负" + result;// 如果是负数，加上"负"
        } else {
            return result;
        }
    }

    // 将字符串转为int数组
    private static int[] toIntArray(String number) {
        // 初始化一维数组长度
        int[] array = new int[number.length()];
        // 循环遍历赋值
        for (int i = 0; i < number.length(); i++) {
            array[i] = Integer.parseInt(number.substring(i, i + 1));
        }
        return array;
    }

    // 将整数部分转为大写的金额
    public static String getChineseInteger(int[] integers) {
        StringBuilder chineseInteger = new StringBuilder();
        int length = integers.length;
        // 对于输入的字符串为 "0." 存入数组后为 0
        if (length == 1 && integers[0] == 0) {
            return "";
        }
        for (int i = 0; i < length; i++) {
            String key = "";
            if (integers[i] == 0) {
                if ((length - i) == 9) {// 亿
                    key = IUNIT[8];
                } else if ((length - i) == 5) {// 万
                    key = IUNIT[4];
                } else if ((length - i) == 1) {// 元
                    key = IUNIT[0];
                }
                if ((length - i) > 1 && integers[i + 1] != 0) {
                    key += NUMBERS[0];
                }
            }
            chineseInteger.append(integers[i] == 0 ? key : (NUMBERS[integers[i]] + IUNIT[length - i - 1]));
        }
        return chineseInteger.toString();
    }

    // 将小数部分转为大写的金额
    private static String getChineseDecimal(int[] decimals) { // 角 分 厘 038 壹分捌厘
        StringBuffer chineseDecimal = new StringBuffer("");
        for (int i = 0; i < decimals.length; i++) {
            if (i == 3) {
                break;
            }
            chineseDecimal.append(decimals[i] == 0 ? "" : (NUMBERS[decimals[i]] + DUNIT[i]));
        }
        return chineseDecimal.toString();
    }

    public static void main(String[] args) {
        String number = "-7004.142";
        String afterStr = toChinese(number);
        System.out.println(number + ": " + afterStr);

        String number2 = "12345";
        System.out.println(Integer2Chinese(number2));
    }


    private static String Integer2Chinese(String strNum) {
        // 需要校验，首位是否为+-号...
        // 这里就先跳过前置校验，先专注于实现核心逻辑
        String[] nums = new String[]{"零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十"};
        String[] symbol = new String[]{"", "十", "百", "千", "万", "十", "百", "千", "亿"};

        int[] intNums = new int[strNum.length()];
        for (int i = 0; i < strNum.length(); i++) {
            intNums[i] = Integer.parseInt(strNum.substring(i, i + 1));
        }

        StringBuilder ans = new StringBuilder();
        int n = intNums.length;
        for (int i = 0; i < n; i++) {
            String key = "";
            if (intNums[i] == 0) {
                  if (n - i == 9) {
                      key = symbol[8];
                  } else if (n - i == 5) {
                      key = symbol[4];
                  } else if (n - i == 1) {
                      key = symbol[0];
                  }

                  if (i + 1 < n && intNums[i + 1] != 0) {
                      key += nums[0];
                  }
            }

            ans.append(intNums[i] == 0 ? key : nums[intNums[i]] + symbol[n - i - 1]);
        }

        return ans.toString();
    }
}