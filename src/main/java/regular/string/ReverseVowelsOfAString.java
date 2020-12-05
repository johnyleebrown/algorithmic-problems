package regular.string;

/**
 * 345
 * Write a function that takes a string as input and reverse only the vowels of a string.
 */
public class ReverseVowelsOfAString {

    class Solution {
        public String reverseVowels(String s) {
            boolean[] map = new boolean[256];
            map['a'] = true;
            map['A'] = true;
            map['e'] = true;
            map['E'] = true;
            map['i'] = true;
            map['I'] = true;
            map['o'] = true;
            map['O'] = true;
            map['u'] = true;
            map['U'] = true;
            char[] chars = s.toCharArray();
            for (int l = 0, r = chars.length - 1; l < r; ++l, --r) {
                while (l < r && !map[chars[l]]) ++l;
                while (l < r && !map[chars[r]]) --r;
                if (l < r) {
                    char tmp = chars[l];
                    chars[l] = chars[r];
                    chars[r] = tmp;
                }
            }
            return new String(chars);
        }
    }
}
