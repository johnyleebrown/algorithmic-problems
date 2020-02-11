package string;

import java.util.Stack;

/**
 * 20
 *
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */
public class ValidParentheses {
    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static boolean solution(String s) {
        Stack<Character> st = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '{') st.push('}');
            else if (c == '[') st.push(']');
            else if (c == '(') st.push(')');
            else if (st.isEmpty() || st.pop() != c) return false;
        }

        return st.isEmpty();
    }
}
