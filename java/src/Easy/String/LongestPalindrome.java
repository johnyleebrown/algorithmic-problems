package Easy.String;

import java.util.HashSet;

/**
 * 409
 Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.
 This is case sensitive, for example "Aa" is not considered a palindrome here.
 */
public class LongestPalindrome {

    //O(n), O(n)
    class Solution {
        public int longestPalindrome(String s) {
            char chars[] = s.toCharArray();
            int lowerCount[] = new int[26];
            int upperCount[] = new int[26];
            int odds = 0;
            int n = s.length();
            for (int i = 0; i < n; i++) {
                if (chars[i] < 'a') upperCount[chars[i] - 'A']++;
                else lowerCount[chars[i] - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                if (lowerCount[i] % 2 == 1) odds++;
                if (upperCount[i] % 2 == 1) odds++;
            }
            if (odds == 0) return n;
            return n - odds + 1;
        }
    }

    //O(n), O(n)
     class Solution2 {
        public int longestPalindrome(String s) {
            if (s == null || s.length() == 0) return 0;
            HashSet<Character> hs = new HashSet<>();
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
                if (hs.contains(s.charAt(i))) {
                    hs.remove(s.charAt(i));
                    count++;
                } else hs.add(s.charAt(i));
            }
            if (!hs.isEmpty()) return count * 2 + 1;
            return count * 2;
        }
    }
}
