package bs.range;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2080. Range Frequency Queries
 *
 * https://leetcode.com/problems/range-frequency-queries/
 */
public class RangeFrequencyQueries {

  /**
   * Range binary search of indexes where specific value occurs since it's
   * indexes are increasing. Using prefix array logic we can understand how many
   * times specific value occurs.
   */
  public static class RangeFreqQuery {

    Map<Integer, List<Integer>> map = new HashMap<>();

    public RangeFreqQuery(int[] arr) {
      for (int i = 0; i < arr.length; i++) {
        map.putIfAbsent(arr[i], new ArrayList<>());
        map.get(arr[i]).add(i);
      }
    }

    public int query(int left, int right, int value) {
      List<Integer> A = map.get(value);
      if (A == null || left > A.get(A.size() - 1) || right < A.get(0)) {
        return 0;
      }
      // ..left->i...j<-right..
      int i = upperBound(A, left), j = lowerBound(A, right);
      return j - i + 1;
    }

    int upperBound(List<Integer> a, int x) {
      int lo = -1, hi = a.size();
      while (hi - lo > 1) {
        int mid = lo + (hi - lo) / 2;
        if (a.get(mid) < x) lo = mid;
        else hi = mid;
      }
      return hi;
    }

    int lowerBound(List<Integer> a, int x) {
      int lo = -1, hi = a.size();
      while (hi - lo > 1) {
        int mid = lo + (hi - lo) / 2;
        if (a.get(mid) <= x) lo = mid;
        else hi = mid;
      }
      return lo;
    }
  }
}