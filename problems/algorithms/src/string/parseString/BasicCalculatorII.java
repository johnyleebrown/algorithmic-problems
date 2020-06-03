package string.parseString;

/**
 * 227
 *
 * ======
 *
 * Task.
 *
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string contains only non-negative integers, +, -, *, /
 * operators and empty spaces . The integer division should truncate toward
 * zero.
 *
 * ======
 *
 * Source: Leetcode
 */
public class BasicCalculatorII {
    /**
     * If digit - parse digit all the way. Keep cur for global answer and st(for
     * stack) for local answer that is being calculated with * and /.
     */
    public static class Solution {
        int i = 0;

        public int calculate(String s) {
            char[] ca = s.toCharArray();
            Integer st = null;
            char sign = 0;
            boolean stMinus = false;
            int cur = 0;
            while (i < ca.length) {
                char c = ca[i];
                if (c != ' ') {
                    if (Character.isDigit(c)) {
                        int x = getNumber(ca);
                        if (st == null) st = x;
                        else st = calc(st, sign, x);
                        continue;
                    } else {
                        if (c == '+' || c == '-') {
                            if (stMinus) cur -= st;
                            else cur += st;
                            st = null;
                            if (c == '-') {
                                stMinus = true;
                            } else {
                                stMinus = false;
                            }
                        } else {
                            sign = c;
                        }
                    }
                }
                i++;
            }
            if (stMinus) cur -= st;
            else cur += st;
            return cur;
        }

        int getNumber(char[] ca) {
            int num = 0;
            while (i < ca.length && Character.isDigit(ca[i])) {
                num = num * 10 + (ca[i] - '0');
                i++;
            }
            return num;
        }

        int calc(int a, char s, int b) {
            if (s == '*') {
                return a * b;
            } else if (s == '/') {
                return a / b;
            } else return 1;
        }
    }
}