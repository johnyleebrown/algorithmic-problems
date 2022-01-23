package string.other;

import java.util.Stack;

/**
 * 682
 */
public class BaseballGame {
    class Solution {
        public int calPoints(String[] ops) {
            Stack<Integer> s = new Stack<>();
            for (int i = 0; i < ops.length; i++) {
                if (ops[i].equals("+")) s.push(s.elementAt(s.size() - 2) + s.peek());
                else if (ops[i].equals("C")) s.pop();
                else if (ops[i].equals("D")) s.push(2 * s.peek());
                else s.push(Integer.parseInt(ops[i]));
            }
            int sum = 0;
            while (s.size() != 0) sum += s.pop();
            return sum;
        }
    }
}
