package string;

/**
 * 344
 * Write a function that takes a string as input and returns the string reversed.
 */
public class ReverseString {

    // O(n/2), O(n)
    class Solution {
        public String reverseString(String s) {
            if (s.length() < 2) return s;
            int l = 0;
            int r  = s.length() - 1;
            char[] letters = s.toCharArray();
            while (l < r){
                char temp = letters[l];
                letters[l] = letters[r];
                letters[r] = temp;
                l++;
                r--;
            }
            return new String(letters);
        }
    }
}
