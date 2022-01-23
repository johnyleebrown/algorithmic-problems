package string.other;

import java.util.Stack;

/**
 * 20
 */
public class ValidParentheses {
    public static boolean Solution1(String s) {
        Stack<Character> st = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '{') st.push('}');
            else if (c == '[') st.push(']');
            else if (c == '(') st.push(')');
            else if (st.isEmpty() || st.pop() != c) return false;
        }

        return st.isEmpty();
    }

    public static class Solution2 {
        public boolean isValid(String s) {
            Stack<Character> st = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char curChar = s.charAt(i);
                if (isClosingBracket(curChar)) {
                    if (st.isEmpty() || !isSameTypeBrackets(st.pop(), curChar)) {
                        return false;
                    }
                } else {
                    st.add(curChar);
                }
            }
            return st.size() == 0;
        }

        private boolean isClosingBracket(char c) {
            return c == ')' || c == '}' || c == ']';
        }

        private boolean isSameTypeBrackets(char c1, char c2) {
            return (c1 == '(' && c2 == ')')
                    || (c1 == '[' && c2 == ']')
                    || (c1 == '{' && c2 == '}');
        }
    }
}
