package Medium.String;

import java.util.HashMap;
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
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static int solution(String s) {
        Set<Character> set = new HashSet<>();
        char[] ca = s.toCharArray();
        int ans = 0, i = 0, j = 0;

        while (i < s.length() && j < s.length()) {
            if (!set.contains(ca[j])) {
                set.add(ca[j++]);
                ans = Math.max(ans, j - i);
            } else {
                set.remove(ca[i++]);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(solution("abaaaaaaaaaabaaaa"));
        /*
        abcabcbb
         */
    }
}
