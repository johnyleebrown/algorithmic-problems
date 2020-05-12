package twoPointers.slidingWindow.count;

/**
 * StringWithoutKIdenticalConsecutiveLetters
 *
 * ======
 *
 * Task.
 *
 * Validate that a sequence of coins does not violate the property: no more than
 * 2 in a row face the same direction.
 *
 * ======
 *
 * Company : Google
 * Source: Leetcode
 */
public class StringWithoutKIdenticalConsecutiveLetters {
    public static class Solution {
        public static boolean solve(String s, int k) {
            int n = s.length();
            if (n == 0) return false;
            if (k > n) return true;
            int r = 0;
            int[] m = new int[26];
            for (; r < k; r++) {
                m[s.charAt(r) - 'A']++;
            }
            for (int l = 0; r < n; r++, l++) {
                m[s.charAt(r) - 'A']++;
                if (m[s.charAt(r) - 'A'] > k) {
                    return false;
                }
                m[s.charAt(l) - 'A']--;
            }
            return true;
        }
    }
}