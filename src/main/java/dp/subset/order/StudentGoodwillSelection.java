package dp.subset.order;

import java.util.HashSet;
import java.util.Set;

/**
 * You have two arrays of size n, first one is the amount of goodwill and second is the heights of
 * the respective students. You have to count the total number of unique selection of atleast one
 * student such that the sum of their goodwill is greater than k and their heights are in strictly
 * increasing order(you cannot reorder the students, i.e you have to select them from left to right
 * such that for 0<=i<j<=n, h[i]<h[j] where h is the height array).
 *
 * <p>Constraints: 0<n<40 G[i],H[i] and k can be as large as 10^9
 *
 * <p>Eg:
 *
 * <p>n = 4 g = [1,2,3,6] h=[2,6,7,5] k=5
 *
 * <p>ans = 3
 *
 * <p>Explanation:
 *
 * <p>Student with height 5 Student with height 2,5 Student with height 2,6,7
 *
 * <p>https://leetcode.com/discuss/interview-question/1531799/Amazon-SDE-coding-assessment
 */
public class StudentGoodwillSelection {
  /**
   * BF Backtracking
   *
   * <p>must be unique students (indexes) - set of sets - set of hashes
   */
  public static class Solution1 {
    public int solve(int n, int[] goodwill, int[] heights, int k) {
      Set<String> set = new HashSet<>();
      for (int i = 0; i < n; i++) {
        dfs(0, i, goodwill, heights, k, set, new StringBuilder());
      }
      return set.size();
    }

    public void dfs(
        int sum, int i, int[] goodwill, int[] heights, int k, Set<String> set, StringBuilder sb) {
      sum += goodwill[i];
      // record current subset
      String curIndexString = "," + i;
      sb.append(curIndexString);
      // record result
      if (sum > k) {
        if (!set.add(sb.toString())) {
          System.out.println(sb.toString());
        }
      }
      // make the next choice
      for (int j = i + 1; j < goodwill.length; j++) {
        if (heights[j] <= heights[i]) {
          continue;
        }
        dfs(sum, j, goodwill, heights, k, set, sb);
      }
      sb.delete(sb.length() - curIndexString.length(), sb.length());
    }
  }
}
