package a0.string.regular;

/**
 * 681
 * Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits.
 * There is no limit on how many times a digit can be reused.
 * You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.
 */
public class NextClosestTime {
    class Solution {
        int[] mins = {600, 60, 10, 1};

        public String nextClosestTime(String time) {
            int colon = time.indexOf(':');
            int cur = Integer.valueOf(time.substring(0, colon)) * 60 + Integer.valueOf(time.substring(colon + 1));
            char[] next = new char[4];
            for (int i = 1, d = 0; i <= 1440 && d < 4; i++) {
                int m = (cur + i) % 1440;
                for (d = 0; d < 4; d++) {
                    next[d] = (char) ('0' + m / mins[d]);
                    m %= mins[d];
                    if (time.indexOf(next[d]) == -1) break;
                }
            }
            return new String(next, 0, 2) + ':' + new String(next, 2, 2);
        }
    }
}
