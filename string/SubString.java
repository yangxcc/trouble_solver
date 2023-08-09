package string;

// 字符串减法，和加法类似
public class SubString {
    public static void main(String[] args) {
        System.out.println(sub("12", "12"));
    }

    private static String sub(String a, String b) {
        int i = a.length() - 1, j = b.length() - 1;
        int borrow = 0;
        StringBuilder ans = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int x = i >= 0 ? a.charAt(i) - '0' : 0;
            int y = j >= 0 ? b.charAt(j) - '0' : 0;
            int z = (x - y - borrow + 10) % 10;
            ans.append(z);
            borrow = (x - y - borrow) < 0 ? 1 : 0;
            i--;
            j--;
        }

        // 前往不要忘记reverse
        ans.reverse();

        // 需要去掉前导零
        int pos = 0;
        while (pos < ans.length()) {
            if (ans.charAt(pos) != '0') {
                break;
            }
            pos++;
        }
        return ans.substring(pos).length() == 0 ? "0" : ans.substring(pos);
    }
}
