package string;

import java.util.Stack;

/**
 * 682
 * You're now a baseball game point recorder.
 * Given a list of strings, each string can be one of the 4 following types:
 * Integer (one round's score): Directly represents the number of points you get in this round.
 * "+" (one round's score): Represents that the points you get in this round are the sum of the last two valid round's points.
 * "D" (one round's score): Represents that the points you get in this round are the doubled data of the last valid round's points.
 * "C" (an operation, which isn't a round's score): Represents the last valid round's points you get were invalid and should be removed.
 * Each round's operation is permanent and could have an impact on the round before and the round after.
 * You need to return the sum of the points you could get in all the rounds.
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
