package a0.two.regular;

/**
 * 844
 *
 * ======
 *
 * Task.
 *
 * Given two strings S and T, return if they are equal when both are typed into
 * empty text editors. # means a backspace character.
 */
public class BackspaceStringCompare {
    public static class Solution {
        public boolean backspaceCompare(String s, String t) {
            int sn = s.length(), tn = t.length();
            int si = sn - 1, ti = tn - 1;
            while (si >= 0 || ti >= 0) {
                if (si >= 0) {
                    si = getIndexAfterRemovals(s, si);
                }
                if (ti >= 0) {
                    ti = getIndexAfterRemovals(t, ti);
                }
                if (si < 0 && ti < 0) {
                    return true;
                }
                if ((si < 0 || ti < 0) || s.charAt(si) != t.charAt(ti)) {
                    return false;
                }
                si--;
                ti--;
            }
            return true;
        }

        private int getIndexAfterRemovals(String s, int i) {
            if (s.charAt(i) == '#') {
                int c = 1;
                while (c > 0) {
                    c--;
                    i--;
                    if (i < 0) break;
                    if (s.charAt(i) == '#') c++;
                    i--;
                    if (i < 0) break;
                    if (s.charAt(i) == '#') c++;
                }
            } else {
            }
            return i;
        }
    }
}
