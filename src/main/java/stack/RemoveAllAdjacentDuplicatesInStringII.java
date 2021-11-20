package stack;

import java.util.ArrayList;
import java.util.List;

/**
 * 1209. Remove All Adjacent Duplicates in String II
 *
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
 */
public class RemoveAllAdjacentDuplicatesInStringII {

  /**
   * Use stack of array of char counts. aabbbc => [{a,2},{b,3},{c,1}]. If we
   * encounter same char - we modify the count - if it's k - we remove the char
   * from stack.
   */
  public static class Solution {

    public String removeDuplicates(String s, int k) {

      List<Item> st = new ArrayList<>();
      for (int i = 0; i < s.length(); i++) {
        int n = st.size();
        char c = s.charAt(i);
        if (!st.isEmpty() && st.get(n - 1).c == c) {
          // same char - increase count
          st.get(n - 1).count++;
          // can trim
          if (st.get(n - 1).count == k) {
            st.remove(n - 1);
          }
        } else {
          st.add(new Item(c, 1));
        }
      }

      // transform to string
      StringBuilder sb = new StringBuilder();
      for (Item it : st) {
        while (--it.count >= 0) {
          sb.append(it.c);
        }
      }

      return sb.toString();
    }

    private static class Item {

      char c;
      int count;

      Item(char c, int count) {
        this.c = c;
        this.count = count;
      }
    }
  }

  /**
   * Solution with replacements (2 pointers) - we move second pointer value
   * closer to i, so we could compare values - to see if we have dups.
   */
  public static class Solution2 {

    public String removeDuplicates(String s, int k) {
      int i = 0, n = s.length();
      int count[] = new int[n];
      char[] stack = s.toCharArray();
      for (int j = 0; j < n; ++j, ++i) {
        stack[i] = stack[j];
        count[i] = 1;
        if (i > 0 && stack[i - 1] == stack[j]) {
          count[i] = count[i - 1] + 1;
        }
        if (count[i] == k) {
          i -= k;
        }
      }
      return new String(stack, 0, i);
    }
  }
}