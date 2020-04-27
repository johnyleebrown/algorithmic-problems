package twoPointers.slidingWindow.count;

import util.tester.Tester;

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
    public static void main(String[] args) {
        new Tester(new Solution())
                .add("TFTFTF", 2).expect(true)
                .add("TTFTTF", 2).expect(true)
                .add("FFTFFT", 2).expect(true)
                .add("TTT", 2).expect(false)
                .add("FFF", 2).expect(false)
                .add("FTTTF", 2).expect(false)
                .add("TFFFT", 2).expect(false)
                .add("TFFAAAAFT", 3).expect(false)
                .run();
    }

    public static class Solution {
        public boolean solve(String s, int k) {
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