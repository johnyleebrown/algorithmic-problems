package string.other;

import java.util.ArrayList;
import java.util.List;

/**
 * 500
 * Given a List of words, return the words that can be typed using letters
 * of alphabet on only one row's of American keyboard like the image below.
 * You may use one character in the keyboard more than once.
 * You may assume the input string will only contain letters of alphabet.
 */
public class KeyboardRow {
    class Solution {
        public String[] findWords(String[] words) {
            if (words == null || words.length == 0) return words;
            List<String> list = new ArrayList();
            int arr[] = new int[26];
            arr['a' - 'a'] = arr['s' - 'a'] = arr['d' - 'a'] = arr['f' - 'a'] = arr['g' - 'a'] = arr['h' - 'a'] = arr['j' - 'a'] = arr['k' - 'a'] = arr['l' - 'a'] = 1;
            arr['z' - 'a'] = arr['x' - 'a'] = arr['c' - 'a'] = arr['v' - 'a'] = arr['b' - 'a'] = arr['n' - 'a'] = arr['m' - 'a'] = 2;
            a:
            for (String word : words) {
                String temp = word.toLowerCase();
                for (int i = 1; i < temp.length(); i++)
                    if (arr[temp.charAt(i) - 'a'] != arr[temp.charAt(i - 1) - 'a']) continue a;
                list.add(word);
            }
            return list.toArray(new String[list.size()]);
        }
    }
}
