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
    static class Solution {
        public int lengthOfLongestSubstring(String s) {
            int l = 0;
            int result = 0;
            int repeatedCharsCount = 0;
            int[] map = new int[256];

            for (int r = 0; r < s.length(); r++) {
                map[s.charAt(r)]++;
                // the char began being repeated
                if (map[s.charAt(r)] == 2) {
                    repeatedCharsCount++;
                }

                while (repeatedCharsCount > 0) {
                    map[s.charAt(l)]--;
                    // the char stopped being repeated
                    if (map[s.charAt(l)] == 1) {
                        repeatedCharsCount--;
                    }

                    l++;
                }

                result = Math.max(result, r - l + 1);
            }

            return result;
        }
    }
}
