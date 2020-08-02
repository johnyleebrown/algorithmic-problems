package string.parseString.other;

import java.util.HashMap;
import java.util.Map;

/**
 * 13
 *
 * ======
 *
 * Task.
 *
 * Roman numerals are usually written largest to smallest from left to right.
 * However, the numeral for four is not IIII. Instead, the number four is
 * written as IV. Because the one is before the five we subtract it making four.
 * The same principle applies to the number nine, which is written as IX. There
 * are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 *
 * Given a roman numeral, convert it to an integer. Input is guaranteed to be
 * within the range from 1 to 3999.
 *
 * ======
 *
 * Source: Leetcode
 */
public class RomanToInteger {
    /**
     * SF.
     */
    public static class Solution {
        public int romanToInt(String s) {
            Map<Character, Integer> val = new HashMap<>();
            val.put('I', 1);
            val.put('V', 5);
            val.put('X', 10);
            val.put('L', 50);
            val.put('C', 100);
            val.put('D', 500);
            val.put('M', 1000);
            Map<Character, Character> prev = new HashMap<>();
            prev.put('V', 'I');
            prev.put('X', 'I');
            prev.put('L', 'X');
            prev.put('C', 'X');
            prev.put('D', 'C');
            prev.put('M', 'C');
            int res = 0;
            char[] ca = s.toCharArray();
            int i = ca.length - 1;
            while (i >= 0) {
                if (i - 1 >= 0 && prev.containsKey(ca[i]) && prev.get(ca[i]) == ca[i - 1]) {
                    res += val.get(ca[i]) - val.get(ca[i - 1]);
                    i -= 2;
                } else {
                    res += val.get(ca[i]);
                    i--;
                }
            }
            return res;
        }
    }
}