package backtracking.other;

import java.util.ArrayList;
import java.util.List;

/**
 * 282
 */
public class ExpressionAddOperators {

  /**
   * BT with sending next index + 2 loops for length and operations.
   * Evaluate number from string every time we append new stuff.
   * We don't need to add operation if index is 0.
   * Keep list of string operands, so we could easily evaluate.
   * In each step add operator and operand, edge case index==0.
   */
  public static class Solution {

    private static final String[] ops = new String[]{"+", "-", "*"};
    List<String> ans = new ArrayList<>();

    public List<String> addOperators(String num, int target) {
      gen(0, "", 0, num, target, new ArrayList<>());
      return ans;
    }

    private void gen(long sum, String sumString, int i, String num, long target,
        List<String> sums) {
      if (sum == target && i == num.length()) {
        ans.add(sumString);
      } else {
        for (int len = 1;
             i < num.length() && len <= (num.charAt(i) == '0' ? 1 : num.length() - i);
             len++) {
          String newOpnd = num.substring(i, i + len);
          if (i == 0) {
            sums.add(newOpnd);
            gen(Long.parseLong(newOpnd), newOpnd, i + len, num, target, sums);
            sums.remove(sums.size() - 1);
          } else {
            for (String op : ops) {
              sums.add(op);
              sums.add(newOpnd);
              gen(evaluate(sums), sumString + op + newOpnd, i + len, num, target, sums);
              sums.remove(sums.size() - 1);
              sums.remove(sums.size() - 1);
            }
          }
        }
      }
    }

    /**
     * ops should go in pattern num:op:num:op:num
     * evaluate number from string every time
     *
     * idea is to keep prev and prev sign
     * if next sign is multiplication, wait until we perform multiplication on the right,
     * then add to prev. if no mult - just add to prev.
     */
    private long evaluate(List<String> l) {
      int i = 0;
      long prev = 0, prevSign = 1;
      while (i < l.size()) {
        String cur = l.get(i);
        char c = cur.charAt(0);
        if (isDigit(c)) {
          long curNum = Long.parseLong(cur);
          while (i + 1 < l.size() && l.get(i + 1).charAt(0) == '*') {
            String next = l.get(i + 2);
            long nextNum = Long.parseLong(next);
            curNum *= nextNum;
            i += 2;
          }
          prev = prev + curNum * prevSign;
        } else {
          if (c == '+') prevSign = 1;
          else prevSign = -1;
        }
        i++;
      }
      return prev;
    }

    private boolean isDigit(char c) {
      return c >= '0' && c <= '9';
    }
  }

  /**
   * Using a trick to do dynamic evaluation.
   */
  public static class Solution2 {

  }
}
