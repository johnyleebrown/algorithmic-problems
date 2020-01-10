package Easy.String;

import java.util.HashMap;

/**
 * 383
 Given an arbitrary ransom note string and another string containing letters from all the magazines,
 write a function that will return true if the ransom note can be constructed from the magazines ;
 otherwise, it will return false.
 Each letter in the magazine string can only be used once in your ransom note.
 */
public class RansomNote {

    // O(n), O(n)
    class Solution {
        public boolean canConstruct(String ransomNote, String magazine) {
            HashMap<Character, Integer> map = new HashMap<>();
            for (char c : magazine.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);
            for (char c : ransomNote.toCharArray()) {
                int x = map.getOrDefault(c, 0);
                if (x > 0) map.put(c, x - 1);
                else return false;
            }
            return true;
        }
    }

    // O(n), O(1)
    class Solution2 {
        public boolean canConstruct(String ransomNote, String magazine) {
            int[] arr = new int[26];
            for (char c : magazine.toCharArray()) arr[c - 'a']++;
            for (char c : ransomNote.toCharArray()) if (--arr[c - 'a'] < 0) return false;
            return true;
        }
    }
}
