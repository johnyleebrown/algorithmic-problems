package twoPointers.slidingWindow.count;

/**
 * 1358
 *
 * ======
 *
 * Task.
 *
 * Given a string s consisting only of characters a, b and c.
 *
 * Return the number of substrings containing at least one occurrence of all
 * these characters a, b and c.
 *
 * ======
 *
 * Source: Leetcode
 */
public class NumberOfSubstringsContainingAllThreeCharacters {
    /**
     * Shorting the window when we have good condition. Meanwhile, we update
     * result with number of substrings we can have with substring from l to r.
     * n - r means we count all substrings that end at l.
     */
    private static class Solution {
        public int numberOfSubstrings(String s) {
            int l = 0;
            int n = s.length();
            int res = 0;
            int[] c = new int[3];
            for (int r = 0; r < n; r++) {
                c[s.charAt(r) - 'a']++;
                while (c[0] >= 1 && c[1] >= 1 && c[2] >= 1) {
                    res += n - r;//n strings containing substring from l to r
                    c[s.charAt(l) - 'a']--;
                    l++;
                }
            }
            return res;
        }
    }
}