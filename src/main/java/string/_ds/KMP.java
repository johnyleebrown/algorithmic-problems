package string._ds;

/**
 * This implementation uses a version of the Knuth-Morris-Pratt substring search
 * algorithm.
 *
 * Explanation:
 *
 * https://www.youtube.com/watch?v=bH45DuZjBWw&list=PLrS21S1jm43ie9vkDOu3zZqlTtPd1pd0t
 *
 * Problems:
 *
 * https://leetcode.com/problems/find-all-good-strings/
 * https://leetcode.com/problems/shortest-palindrome/
 * https://leetcode.com/problems/longest-happy-prefix/
 */
public class KMP {

    /**
     * Prefix array is an array of numbers where each number corresponds to the prefix number for
     * the substring ending at that position. Prefix number is the length of the largest
     * substring that is both a prefix and a suffix of a string.
     * For "ababbaba" the largest substring that is a prefix and a suffix is "aba".
     * For "ab" it is "".
     * For "ababb" it is empty.
     * But it can't be the whole string.
     * @param pattern pattern that we want to search for in text
     * @return the prefix array
     */
    public int[] getPrefixArray(String pattern) {
        int[] p = new int[pattern.length() + 1]; // prefix array
        p[0] = -1; // default value

        for (int i = 1; i <= pattern.length(); i++) {
            int k = p[i - 1];
            while (k >= 0) {
                if (pattern.charAt(i - 1) == pattern.charAt(k)) {
                    break;
                }
                k = p[k];
            }
            p[i] = k + 1;
        }

        return p;
    }

    /**
     * @param p prefix array from the method {@link #getPrefixArray(String)}
     * @param pattern pattern that we are searching for in text
     * @param text the text that the pattern is being searched in
     * @return -1 if the pattern was not found in text, otherwise return the index of the last
     * matched letter from pattern in text
     */
    public int searchText(int[] p, String pattern, String text) {
        int k = -1;
        for (int i = 0; i < text.length(); i++) {
            while (k >= 0) {
                if (text.charAt(i) == pattern.charAt(k)) {
                    break;
                }
                k = p[k];
            }
            k++;
            if (k == pattern.length()) {
                return i;
            }
        }
        return -1;
    }
}
