package String;

/**
 * 205
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character but a character may map to itself.
 * You may assume both s and t have the same length.
 */
public class IsomorphicStrings {

    // beats 96%
    public boolean isIsomorphic(String s, String t) {
        int[] m = new int[512];
        char[] c1 = s.toCharArray();
        char[] c2 = t.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (m[c1[i]] != m[c2[i] + 256]) return false;
            m[c1[i]] = m[c2[i] + 256] = i + 1;
        }
        return true;
    }

    // beats 97%
    public boolean isIsomorphic2(String s, String t) {
        if (s.length() < 2) return true;
        int[] sa = new int[256];
        int[] ta = new int[256];
        char[] c1 = s.toCharArray();
        char[] c2 = t.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (sa[c1[i]] == 0 && ta[c2[i]] == 0) {
                sa[c1[i]] = c2[i];
                ta[c2[i]] = c1[i];
            }
            else if (sa[c1[i]] != c2[i] || ta[c2[i]] != c1[i]) return false;
        }
        return true;
    }
}
