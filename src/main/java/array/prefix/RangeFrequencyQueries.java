package array.prefix;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 2080. Range Frequency Queries
 *
 * https://leetcode.com/problems/range-frequency-queries/
 */
public class RangeFrequencyQueries {

  /**
   * For each value we have indexes where this value occurs. And for each index
   * we keep a count of how many items we have until this position. Basically a
   * Map {value, prefix_sum_tree_map}
   */
  public static class Solution1 {

    class RangeFreqQuery {

      Map<Integer, TreeMap<Integer, Integer>> map = new HashMap<>();

      public RangeFreqQuery(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
          map.putIfAbsent(arr[i], new TreeMap<>());
          map.get(arr[i]).put(i, map.get(arr[i]).size());
        }
      }

      public int query(int left, int right, int value) {
        if (!map.containsKey(value)) return 0;
        TreeMap<Integer, Integer> nums = map.get(value);
        Integer a = nums.ceilingKey(left), b = nums.floorKey(right);
        if (a == null || b == null) return 0;
        return nums.get(b) - nums.get(a) + 1;
      }
    }
  }
}