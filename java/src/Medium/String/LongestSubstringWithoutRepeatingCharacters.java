package Medium.String;

import java.util.HashSet;
import java.util.Set;

/**
 * 3
 *
 * Given a string, find the length of the longest substring without repeating characters.
 * Examples:
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * Given "bbbbb", the answer is "b", with the length of 1.
 * Given "pwwkew", the answer is "wke", with the length of 3.
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstringWithoutRepeatingCharacters {
    /**
     * Delete every repeating character from set
     * and increment starting pos
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static int solution(String s) {
        char[] ca = s.toCharArray();
        int maxLen = 0, i = 0, j = 0;
        Set<Character> set = new HashSet<>();

        while (i < s.length() && j < s.length()) {
            if (!set.contains(ca[i])) {
                set.add(ca[i++]);
                maxLen = Math.max(maxLen, i - j);
            } else {
                set.remove(ca[j++]);
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(solution("abaaaaaaaaaabaaaa"));
        /*
        abcabcbb
         */
    }
}
