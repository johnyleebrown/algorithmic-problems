package twoPointers.slidingWindow.max;

/**
 * 3
 *
 * ======
 *
 * Task.
 *
 * Given a string, find the length of the longest substring without repeating
 * characters.
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static class Solution {
        public int lengthOfLongestSubstring(String s) {
            int l = 0;
            char[] sc = s.toCharArray();
            int[] c = new int[256];
            boolean x = false;
            int ans = 0;
            for (int r = 0; r < sc.length; r++) {
                c[sc[r]]++;
                if (c[sc[r]] == 2) {
                    x = true;
                }
                while (x) {
                    c[sc[l]]--;
                    if (c[sc[l]] == 1) {
                        x = false;
                    }
                    l++;
                }
                ans = Math.max(ans, r - l + 1);
            }
            return ans;
        }
    }
}
