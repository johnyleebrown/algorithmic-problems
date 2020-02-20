package string.regular;

/**
 * 242
 * Company: Yelp
 *
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 */
public class ValidAnagram {
    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public boolean isAnagram(String s, String t) {
        int[] chars = new int[26];

        for (char c : s.toCharArray()) chars[c - 'a']++;
        for (char c : t.toCharArray()) chars[c - 'a']--;
        for (int i : chars) if (i != 0) return false;

        return true;
    }
}
