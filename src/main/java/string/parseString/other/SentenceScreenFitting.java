package string.parseString.other;

/**
 * 418
 *
 * ======
 *
 * Task.
 *
 * Given a rows x cols screen and a sentence represented by a list of non-empty
 * words, find how many times the given sentence can be fitted on the screen.
 *
 * ======
 *
 * Source: Leetcode
 */
public class SentenceScreenFitting {
    /**
     * Adding length of row, trying to see if we land on space, if not we go
     * back until we find space and start over.
     */
    public static class Solution {
        public int wordsTyping(String[] sentence, int n, int m) {
            String s = String.join(" ", sentence) + " ";
            int i = 0;
            int len = s.length();
            for (int j = 0; j < n; j++) {
                i += m;
                if (s.charAt(i % len) == ' ') {
                    i++;
                } else {
                    while (i > 0 && s.charAt((i - 1) % len) != ' ') {
                        i--;
                    }
                }
            }

            return i / s.length();
        }
    }
}