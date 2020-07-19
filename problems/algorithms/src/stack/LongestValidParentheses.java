package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 32
 *
 * ======
 *
 * Task.
 *
 * Given a string containing just the characters '(' and ')', find the length of
 * the longest valid (well-formed) parentheses substring.
 *
 * ======
 *
 * Source: Leetcode
 */
public class LongestValidParentheses {
    /**
     * Same idea as with valid parenthesis - add '(' and the pop it when we at
     * matching ')'.
     * If that happens calculate max length from this point to the point where
     * matching brackets started - because we are not inserting the matching
     * ones.
     * Also make sure that we always have a left edge for the window to calc max
     * length.
     */
    public static class Solution {
        public int longestValidParentheses(String s) {
            Deque<Integer> st = new ArrayDeque<>();
            int res = 0;
            st.add(-1);
            for (int i = 0; i < s.length(); i++) {
                if (st.size() > 1 && s.charAt(i) == ')' && s.charAt(st.peekLast()) == '(') {
                    st.removeLast();
                    res = Math.max(res, i - st.peekLast());
                } else {
                    st.add(i);
                }
            }
            return res;
        }
    }

    /**
     * There are only three cases for a string:
     *
     * '(' are more than ')'
     * '(' are less than ')'
     * '(' and ')' are the same
     *
     * Now, when you scan from left to right, you can only find the correct
     * maxLength for cases 2 and 3, because if it is case 1, where '(' are more
     * than ')' (e.g., "((()"), then left is always greater than right and
     * maxLength does not have the chance to be updated.
     *
     * Similarly, when you scan from right to left, you can only find the
     * maxLength for cases 1 and 3.
     *
     * Therefore, a two-pass scan covers all the cases and is guaranteed to find
     * the correct maxLength
     */
    public static class Solution2 {
        public int longestValidParentheses(String s) {
            int l = 0, r = 0, res = 0, n = s.length();
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '(') {
                    l++;
                } else {
                    r++;
                }
                if (l == r) {
                    res = Math.max(res, l + r);
                } else if (r > l) {
                    l = r = 0;
                }
            }
            l = r = 0;
            for (int i = n - 1; i >= 0; i--) {
                if (s.charAt(i) == '(') {
                    l++;
                } else {
                    r++;
                }
                if (l == r) {
                    res = Math.max(res, l + r);
                } else if (l > r) {
                    l = r = 0;
                }
            }
            return res;
        }
    }
}