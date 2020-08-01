package twoPointers.regular.other;

/**
 * 727
 *
 * ======
 *
 * Task.
 *
 * Given strings S and T, find the minimum (contiguous) substring W of S, so
 * that T is a subsequence of W.
 *
 * If there is no such window in S that covers all characters in T, return the
 * empty string "". If there are multiple such minimum-length windows, return
 * the one with the left-most starting index.
 *
 * ======
 *
 * Source: Leetcode
 */
public class MinimumWindowSubsequence {
    /**
     * 1) Go through S, find T one by one.
     * [..i............]
     * 2) When reach T end, record ans, go backwards.
     * [..|i.......k|..]
     * Found all T (stopped at i2), record ans.
     * [..i..|i2...k|..]
     * 3) Continue from i2+1.
     *
     * 4) Optimization: keep pos array - positions of T letters when we were
     * going
     * right. If while going backwards we find letter from T at position < than
     * in pos that we saved - we break - meaning we can't optimize [i,k] answer
     * to [i2,k].
     */
    public static class Solution {
        int minL = 0;
        int minLen = Integer.MAX_VALUE;

        public String minWindow(String S, String T) {
            char[] s = S.toCharArray();
            int sn = s.length;
            char[] t = T.toCharArray();
            int tn = t.length;
            int[] pos = new int[tn];
            int i = 0;

            while (i < sn) {

                // 1) going forward
                int k = i;
                for (int j = 0; j < tn; j++) {
                    while (k < sn && s[k] != t[j]) k++;
                    pos[j] = k;
                    // cant find anymore, return
                    if (k == sn) return getAns(minLen, minL, S);
                    k++;
                }
                k--; // because of last k++
                recordAns(k, i);

                // 2) going backwards
                int i2 = k - 1;
                boolean foundBetterAnswer = true;
                for (int j = tn - 2; j >= 0; j--) {
                    while (k >= 0 && s[i2] != t[j]) i2--;

                    // 4) optimization
                    if (i2 < pos[j]) {
                        foundBetterAnswer = false;
                        break;
                    }

                    i2--;
                }

                // 3)
                if (foundBetterAnswer) {
                    i2++;
                    recordAns(k, i2);
                    i = i2 + 1;
                } else {
                    i++;
                }
            }

            return getAns(minLen, minL, S);
        }

        void recordAns(int k, int i) {
            if (minLen > k - i) {
                minLen = k - i;
                minL = i;
            }
        }

        String getAns(int len, int l, String s) {
            if (len == Integer.MAX_VALUE) return "";
            return s.substring(l, l + len + 1);
        }
    }
}