package math.count;

/**
 * 1088
 *
 * ======
 *
 * Task.
 *
 * We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9
 * are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively. When 2, 3,
 * 4, 5 and 7 are rotated 180 degrees, they become invalid.
 *
 * A confusing number is a number that when rotated 180 degrees becomes a
 * different number with each digit valid.(Note that the rotated number can be
 * greater than the original number.)
 *
 * Given a positive integer N, return the number of confusing numbers between 1
 * and N inclusive.
 *
 * ======
 *
 * Source: Leetcode
 */
public class ConfusingNumberII {
    /**
     * Count all numbers with good digits, then subtract Strobogrammatic from
     * that.
     */
    public static class Solution {
        static char[][] pairs = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};

        public int confusingNumberII(int N) {
            String s = Integer.toString(N);
            int ans = findTotal(s);
            for (int i = 1; i <= s.length(); i++) {
                char[] cur = new char[i];
                ans -= gen(cur, s, 0, i - 1);
            }
            return ans;
        }

        /**
         * Count the # of numbers containing only some of "01689" that is less
         * than N.
         *
         * Count of numbers - all numbers less than N - for each digit it is
         * number of pair numbers less then cur multiplied by the 5^([l,r]
         * length).
         */
        private int findTotal(String s) {
            if (s.length() == 0) {
                return 1;
            }

            char first = s.charAt(0);
            int ans = count(first) * (int) (Math.pow(5, s.length() - 1));
            if (first == '0' || first == '1' || first == '6' || first == '8' || first == '9') {
                ans += findTotal(s.substring(1));
            }

            return ans;
        }

        /**
         * A helper function that counts the # of chars in "01689" less than
         * given 'c'.
         */
        private int count(char c) {
            int ans = 0;
            for (char[] p : pairs) {
                if (p[0] < c) {
                    ans++;
                }
            }
            return ans;
        }

        /**
         * Count the # of Strobogrammatic numbers (same number when rotated).
         */
        private int gen(char[] cur, String num, int left, int right) {
            if (left > right) {
                String s = new String(cur);
                // if the number is smaller then inout number in length - we definitely can increment ans
                // but when the length is the same we need to make sure that our num is smaller like 123 < 124
                if (s.length() < num.length() || s.compareTo(num) < 0) return 1;
            } else {
                int res = 0;
                for (char[] p : pairs) {
                    cur[left] = p[0];
                    cur[right] = p[1];
                    // we want middle number to be 0 1 or 8 so when turned it would remain the same
                    if ((cur[0] == '0' && cur.length > 1) || (left == right && p[0] != p[1])) {
                        continue;
                    }
                    res += gen(cur, num, left + 1, right - 1);
                }
                return res;
            }
            return 0;
        }
    }
}