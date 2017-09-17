package Medium.String;

/**
 * 678
 *
 * Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:
 * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
 * An empty string is also valid.
 *
 */
public class ValidParenthesisString {

    // O(n), O(1)
    // low - lowest number of open brackets, with *
    // high - highest number of open brackets, where * is ')'
    public boolean checkValidString(String s) {
        int low = 0, high = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                low++;
                high++;
            }
            else if (ch == ')') {
                if (low > 0) low--;
                high--;
            }
            else {
                if (low > 0) low--;
                high++;
            }
            if (high < 0) return false;
        }
        return low == 0;
    }

    //
    //
    public boolean checkValidString2(String s) {
        return dfs(s.toCharArray(), 0, 0, 0);
    }

    private boolean dfs(char[] s, int index, int left, int right) {
        if (index == s.length) return left == right;
        switch(s[index]) {
            case '(': return dfs(s, index+1, left+1, right);
            case ')': return right+1 <= left ? dfs(s, index+1, left, right+1) : false;
            case '*':
                if(dfs(s, index+1, left, right)) return true;
                else if(dfs(s, index+1, left+1, right)) return true;
                else return right+1 <= left ? dfs(s, index+1, left, right+1) : false;
        }
        return false;
    }
}
