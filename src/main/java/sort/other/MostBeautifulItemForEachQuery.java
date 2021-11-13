package sort.other;

import java.util.TreeMap;

/**
 * 2070. Most Beautiful Item for Each Query https://leetcode.com/problems/most-beautiful-item-for-each-query/
 */
public class MostBeautifulItemForEachQuery {

  public static class Solution {

    public int[] maximumBeauty(int[][] items, int[] queries) {
      TreeMap<Integer, Integer> m = new TreeMap<>();
      for (int i = 0; i < items.length; i++) {
        int x = items[i][0];
        if (m.containsKey(x)) {
          m.put(x, Math.max(items[i][1], m.get(x)));
        } else {
          m.put(x, items[i][1]);
        }
      }
      int currentMax = 0;
      for (Integer k : m.keySet()) {
        m.put(k, Math.max(currentMax, m.get(k)));
        currentMax = Math.max(m.get(k), currentMax);
      }
      int[] ans = new int[queries.length];
      for (int i = 0; i < queries.length; i++) {
        Integer x = m.floorKey(queries[i]);
        if (x != null) {
          ans[i] = m.get(x);
        }
      }
      return ans;
    }
  }

  /**
   * too much space
   */
  class Solution2 {

    public int[] maximumBeauty(int[][] items, int[] queries) {
      int maxPrice = 0;
      for (int i = 0; i < queries.length; i++) {
        maxPrice = Math.max(maxPrice, queries[i]);
      }
      int[] maxBeautyForPrice = new int[maxPrice + 1];
      for (int i = 0; i < items.length; i++) {
        if (items[i][0] < maxBeautyForPrice.length) {
          maxBeautyForPrice[items[i][0]] = Math.max(maxBeautyForPrice[items[i][0]], items[i][1]);
        }
      }
      int currentMax = 0;
      for (int i = 0; i < maxBeautyForPrice.length; i++) {
        maxBeautyForPrice[i] = Math.max(currentMax, maxBeautyForPrice[i]);
        currentMax = Math.max(maxBeautyForPrice[i], currentMax);
      }
      int[] ans = new int[queries.length];
      for (int i = 0; i < queries.length; i++) {
        ans[i] = maxBeautyForPrice[queries[i]];
      }
      return ans;
    }
  }

}