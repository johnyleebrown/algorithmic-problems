package a0.string.regular;

/**
 * 383
 */
public class RansomNote {
    class Solution {
        public boolean canConstruct(String r, String m) {
            int[] ma = new int[256];
            for (int i = 0; i < m.length(); i++) {
                ma[m.charAt(i)]++;
            }
            for (int i = 0; i < r.length(); i++) {
                if (ma[r.charAt(i)] == 0) {
                    return false;
                }
                ma[r.charAt(i)]--;
            }
            return true;
        }
    }
}
