package a0.string.regular;

/**
 * 387
 * Given a string, find the first non-repeating character
 * in it and return it's index. If it doesn't exist, return -1.
 */
public class FirstUniqueCharacterInAString {

    // the fastest solution
    // can run through 0 .. 255 ascii characters
    public int firstUniqChar(String s) {
        int min = Integer.MAX_VALUE;
        for (int i = 'a'; i <= 'z'; i++) {
            int idx = s.indexOf(i);
            if ((idx != -1) && (s.lastIndexOf(i) == idx)) min = Math.min(min, idx);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    // storing up to 256 characters
    // Time complexity : O(n)
    public int firstUniqChar2(String s) {
        int freq [] = new int[256];
        for(int i = 0; i < s.length(); i ++) freq [s.charAt(i) - 'a'] ++;
        for(int i = 0; i < s.length(); i ++) if(freq [s.charAt(i) - 'a'] == 1) return i;
        return -1;
    }
}
