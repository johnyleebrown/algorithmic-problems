package Medium.String;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 22
 *
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * For example, given n = 3, a solution set is:
 * [   "((()))",   "(()())",   "(())()",   "()(())",   "()()()" ]
 */
public class GenerateParentheses {
    /**
     * Intuitive - insert '()' at each position
     *
     * Time complexity: O()
     * Space complexity: O(n)
     */
    class Solution1 {
        public List<String> generateParenthesis(int n) {
            LinkedList<String> list = new LinkedList<>();
            if (n <= 0) return list;
            list.add("()");
            if (n == 1) return list;
            helper(list, new HashSet(), n - 2);
            return list;
        }

        void helper(LinkedList<String> list, Set<String> set, int level) {

            int size = list.size();
            while (size-- != 0) {
                String base = list.poll();
                for (int i = 0; i < base.length(); i++) {
                    StringBuilder sb = new StringBuilder(base);
                    sb.insert(i, "()");
                    if (set.add(sb.toString())) list.addLast(sb.toString());
                }

            }

            if (level != 0) helper(list, set, level - 1);
        }


    }

    /**
     * Add by one
     *
     * Time complexity: O(4^n/sqrt(n))
     * Each valid sequence has at most n steps during the backtracking procedure.
     *
     * Space complexity: O(4^n/sqrt(n))
     */
    class Solution2 {
        public List<String> generateParenthesis(int n) {
            List<String> list = new ArrayList<>();
            backtrack(list, "", 0, 0, n);
            return list;
        }

        void backtrack(List<String> list, String str, int open, int close, int max) {
            if (str.length() == max * 2) {
                list.add(str);
                return;
            }
            if (open < max) backtrack(list, str + "(", open + 1, close, max);
            if (close < open) backtrack(list, str + ")", open, close + 1, max);
        }
    }

    public static void main(String[] args) {
        HashSet<StringBuilder> set = new HashSet<>();
        set.add(new StringBuilder("2"));
        set.add(new StringBuilder("2"));
        System.out.println(set.size());
    }
}
