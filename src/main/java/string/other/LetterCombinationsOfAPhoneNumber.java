package string.other;

import java.util.LinkedList;
import java.util.List;

/**
 * 17
 *
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 */
public class LetterCombinationsOfAPhoneNumber {
    /**
     * Time complexity: O(n * (4^n))
     * Space complexity: O(n)
     */
    public List<String> letterCombinations(String digits) {
        List<String> list = new LinkedList<>();
        if (digits.length() == 0) return list;
        char[][] keys = new char[][]{
                {},
                {},
                {'a','b','c'},
                {'d','e','f'},
                {'g','h','i'},
                {'j','k','l'},
                {'m','n','o'},
                {'p','q','r','s'},
                {'t','u','v'},
                {'w','x','y','z'},
        };
        helper("", list, keys, 0, digits);
        return list;
    }

    void helper(String s, List<String> list, char[][] keys, int ind, String digits) {
        if (ind == digits.length())
            list.add(s);
        else
            for (char c : keys[digits.charAt(ind) - '0'])
                helper(s + c, list, keys, ind + 1, digits);
    }
}
