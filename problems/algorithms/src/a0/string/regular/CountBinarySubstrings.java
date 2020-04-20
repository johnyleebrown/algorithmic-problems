package a0.string.regular;

/**
 * 696
 * Give a string s, count the number of non-empty (contiguous) substrings that have the same number of 0's and 1's,
 * and all the 0's and all the 1's in these substrings are grouped consecutively.
 * Substrings that occur multiple times are counted the number of times they occur.
 */
public class CountBinarySubstrings {

    // Linear Scan
    // O(n), O(1)
    class Solution {
        public int countBinarySubstrings(String s) {
            int ans = 0, prev = 0, cur = 1;
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i - 1) != s.charAt(i)) {
                    ans += Math.min(prev, cur);
                    prev = cur;
                    cur = 1;
                } else cur++;
            }
            return ans + Math.min(prev, cur);
        }
    }

    // O(n), O(n)
    class Solution2 {
        public int countBinarySubstrings(String s) {
            int[] groups = new int[s.length()];
            int t = 0;
            groups[0] = 1;
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i - 1) != s.charAt(i)) {
                    groups[++t] = 1;
                } else {
                    groups[t]++;
                }
            }

            int ans = 0;
            for (int i = 1; i <= t; i++) {
                ans += Math.min(groups[i - 1], groups[i]);
            }
            return ans;
        }
    }
}
