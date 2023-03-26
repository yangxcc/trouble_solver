package string;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 22 middle 括号生成
 * 
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 */
public class GenerateParentheses {
    List<String> ans = new ArrayList<>();
    
    public List<String> generateParenthesis(int n) {

        return ans;
    }

    public void process(int leftRest, int rightRest, String path) {
        if (leftRest == 0 && rightRest == 0) {
            ans.add(path);
            return;
        }

        // 可以看出，这两个if表示的是加上一个左括号之后，后面肯定会加上右括号
        // 也就是说优先加上左括号
        if (leftRest > 0) {
            process(leftRest - 1, rightRest, path + "(");
        }

        if (rightRest > leftRest) {
            process(leftRest, rightRest - 1, path + ")");
        }
    }
}
