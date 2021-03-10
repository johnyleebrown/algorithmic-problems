package backtracking.numbers;

/**
 * 556. Next Greater Element III
 *
 * <p>======
 *
 * <p>Given a positive integer n, find the smallest integer which has exactly the same digits
 * existing in the integer n and is greater in value than n. If no such positive integer exists,
 * return -1.
 *
 * <p>Note that the returned integer should fit in 32-bit integer, if there is a valid answer but it
 * does not fit in 32-bit integer, return -1.
 *
 * <p>Example 1:
 *
 * <p>Input: n = 12 Output: 21 Example 2:
 *
 * <p>Input: n = 21 Output: -1
 *
 * <p>Constraints:
 *
 * <p>1 <= n <= 231 - 1
 *
 * <p>======
 *
 * <p>$INSERT_LINK
 */
public class NextGreaterElementIII {
  /**
   * Idea is to generate the next permutation. 2 caveats here - the replacement for the first
   * digit
   * that we will replace should be bigger then the current, because this way the result will be
   * bigger. Then after we found one we fill the rest on increasing order - again, ot have the
   * next
   * bigger result.
   *
   * <p>Similar to backtracking algorithm but we start from the end, we make a step, check, if
   * all
   * good - continue to the end, if not - add to collection, and then continue on the path to
   * start
   * of the string.
   *
   * <p>In this case we use counting sort instead of pq.
   */
  public static class Solution {
    public int nextGreaterElement(int num) {
      int[] ts = new int[10];
      String x = String.valueOf(num);
      StringBuilder sb = new StringBuilder(x);
      int n = x.length();

      for (int k = n - 1; k >= 0; k--) {
        // remove cur
        char cur = sb.charAt(k);
        sb.deleteCharAt(k);

        // find next
        Character next = findNext(ts, cur);

        // put cur in collection
        ts[cur - '0']++;

        // -- if could not find next go to next iter
        if (next == null) continue;

        // -- if found next put next at cur pos, go to the next digit
        sb.append(next);

        // we added next number already, should remove from collection
        ts[next - '0']--;

        // -- -- if in the end and the res is not eq to input ret res
        for (int i = 0; i < ts.length; i++) {
          if (ts[i] == 0) continue;
          for (int j = 0; j < ts[i]; j++) {
            sb.append(i);
          }
        }

        return getAns(sb);
      }

      return -1;
    }

    private Character findNext(int[] ts, char c) {
      int start = c - '0';
      for (int i = start + 1; i < ts.length; i++) {
        if (ts[i] != 0) return (char) (i + '0');
      }
      return null;
    }

    private int getAns(StringBuilder sb) {
      long l = Long.parseLong(sb.toString());
      if (l > Integer.MAX_VALUE) return -1;
      return (int) l;
    }
  }
}
