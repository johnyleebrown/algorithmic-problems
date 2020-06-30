package backtracking.notdp;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 140
 *
 * ======
 *
 * Task.
 *
 * Given a non-empty string s and a dictionary wordDict containing a list of
 * non-empty words, add spaces in s to construct a sentence where each word is
 * a
 * valid dictionary word. Return all such possible sentences.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the
 * segmentation.
 * You may assume the dictionary does not contain duplicate words.
 *
 * ======
 *
 * Source: Leetcode
 */
public class WordBreakII {
    /**
     * Backtracking Solution
     *
     * it's obvious that we ve re-calculate some states
     * so 1st thought - how can we rewrite this to support cache
     * we need to send the rest of the string in dfs
     * and we need to return the partial list that we get from it
     * then we will combine the list with what we already have
     */
    public static class Solution1 {
        List<String> ans;
        Set<String> set;
        Set<String> s2;
        int n;

        public List<String> wordBreak(String s, List<String> d) {
            n = s.length();
            ans = new LinkedList<>();
            if (n == 0) return ans;
            set = new HashSet<>(d);
            s2 = new HashSet<>();
            gen(0, s, "");
            return ans;
        }

        void gen(int ind, String s, String cur) {
            if (ind == n) {
                ans.add(cur);
            } else {
                for (int i = ind + 1; i <= n; i++) {
                    String x = s.substring(ind, i);
                    if (set.contains(x)) {
                        gen(i, s, cur.length() == 0 ? x : cur + " " + x);
                    }
                }
            }
        }
    }

    /**
     * DFS
     */
    public static class Solution2 {

    }

    /**
     * DFS + cache
     */
    public static class Solution3 {

    }
}