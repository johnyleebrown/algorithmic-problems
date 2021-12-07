package array.subarraySum;

import java.util.HashMap;
import java.util.Map;

/**
 * 560
 */
public class SubarraySumEqualsK {

  /**
   * Count prefix sum, if we have such a prefix sum = curElement - k, it means that
   * between cur element and prefix sum element there is sum k.
   */
  public static class Solution {

    public int subarraySum(int[] ar, int k) {
      int prefixSum = 0;
      int ans = 0;
      Map<Integer, Integer> m = new HashMap<>();
      m.put(0, 1);

      for (int curElement : ar) {
        prefixSum += curElement;

        if (m.containsKey(prefixSum - k)) {
          ans += m.get(prefixSum - k);
        }

        m.put(prefixSum, m.getOrDefault(prefixSum, 0) + 1);
      }

      return ans;
    }
  }

  /**
   * Memory O(1)
   */
  public static class Solution2 {

    public int subarraySum(int[] nums, int k) {
      int ans = 0;
      for (int i = 0; i < nums.length; i++) {
        int sum = 0;
        for (int j = i; j < nums.length; j++) {
          sum += nums[j];
          if (sum == k) ans++;
        }
      }
      return ans;
    }
  }
}

