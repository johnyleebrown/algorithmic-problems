package string.regular;

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
    // low  - keeps track of '('
    // However, creating a new character array will take O(n) space
    public boolean checkValidString(String s) {
        int lo = 0, hi = 0;
        for (char c: s.toCharArray()) {
            lo += c == '(' ? 1 : -1;
            hi += c != ')' ? 1 : -1;
            if (hi < 0) break;
            lo = Math.max(lo, 0);
        }
        return lo == 0;
    }

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
                if (dfs(s, index+1, left, right)) return true;
                else if (dfs(s, index+1, left+1, right)) return true;
                else return right+1 <= left ? dfs(s, index+1, left, right+1) : false;
        }
        return false;
    }
}
