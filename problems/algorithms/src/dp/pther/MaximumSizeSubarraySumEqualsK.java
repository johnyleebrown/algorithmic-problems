package dp.pther;

import java.util.HashMap;

/**
 * 325
 */
public class MaximumSizeSubarraySumEqualsK {
    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public int maxSubArrayLen(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0, res = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) // sum - k = the entry where the list should start
                res = Math.max(res, i - map.get(sum - k));
            else
                map.put(sum, i);
        }

        return res;
    }
}
