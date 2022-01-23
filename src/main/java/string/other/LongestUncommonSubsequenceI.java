package string.other;

/**
 * 521
 * Longest Uncommon Subsequence I
 */
public class LongestUncommonSubsequenceI {
    class Solution {
        public int findLUSlength(String a, String b) {
            return a.equals(b) ? -1 : Math.max(a.length(), b.length());
        }
    }
}
