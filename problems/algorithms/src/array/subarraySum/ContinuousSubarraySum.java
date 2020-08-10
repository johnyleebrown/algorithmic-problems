package array.subarraySum;

import java.util.HashMap;
import java.util.Map;

/**
 * 523
 */
public class ContinuousSubarraySum {
    /**
     * In this case i keep by the example of 974 - remainder as key and as value
     * - first index of this remainder so we could see if we can use that prefix
     * sum with the remainder in the answer.
     */
    public static class Solution {
        public boolean checkSubarraySum(int[] a, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, -1);
            int sum = 0;
            for (int i = 0; i < a.length; i++) {
                sum += a[i];
                int x = k == 0 ? sum : sum % k;
                if (i - map.getOrDefault(x, i) > 1) return true;
                if (!map.containsKey(x)) {
                    map.put(x, i);
                }
            }
            return false;
        }
    }
}
