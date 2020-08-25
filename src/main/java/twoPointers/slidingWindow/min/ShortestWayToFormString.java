package twoPointers.slidingWindow.min;

import java.util.Arrays;

/**
 * 1055
 */
public class ShortestWayToFormString {
    /**
     * We create inverted index to see what position the letter is in after
     * current position. Then
     */
    public static class Solution1 {
        public int shortestWay(String S, String T) {
            int sn = S.length();
            int[][] pos = invert(S);
            int sInd = 0;
            int ans = 0;
            for (char c : T.toCharArray()) {
                // cant find in whole word
                if (pos[0][c - 'a'] == -1) {
                    return -1;
                }

                int curLetterPosAfterInd = pos[sInd][c - 'a'];

                // cant find from sInd to end of S
                if (curLetterPosAfterInd == -1) {
                    ans++;

                    // after we couldn't find it from sInd
                    // we continue from 0, we have to finish search in
                    // current iteration
                    sInd = pos[0][c - 'a'] + 1;
                } else {
                    sInd = curLetterPosAfterInd + 1;
                }

                if (sInd == sn) {
                    sInd = 0;
                    ans++;
                }

            }

            return sInd == 0 ? ans : ans + 1;
        }

        private int[][] invert(String s) {
            int n = s.length();
            int[][] pos = new int[n][26];

            // fill the last character
            Arrays.fill(pos[n - 1], -1);
            pos[n - 1][s.charAt(n - 1) - 'a'] = n - 1;

            for (int i = n - 2; i >= 0; i--) {
                pos[i] = Arrays.copyOf(pos[i + 1], 26);
                pos[i][s.charAt(i) - 'a'] = i;
            }

            return pos;
        }
    }

    /**
     * Regular.
     */
    public static class Solution2 {
        public int shortestWay(String S, String T) {
            int ans = 1;
            int prev = 1;
            char[] s = S.toCharArray();
            int sn = s.length;
            int si = 0;
            char[] t = T.toCharArray();
            int tn = t.length;
            int ti = 0;

            while (ti < tn) {
                if (si >= sn) {
                    si = 0;
                    ans++;
                }
                if (prev + 2 == ans) {
                    return -1;
                }

                if (s[si] != t[ti]) {
                    while (si < sn && s[si] != t[ti]) {
                        si++;
                    }
                } else {
                    prev = ans;
                    si++;
                    ti++;
                }
            }

            return ans;
        }
    }
}
