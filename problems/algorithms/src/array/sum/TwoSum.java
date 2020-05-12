package array.sum;

import java.util.HashMap;

/**
 * 1
 */
public class TwoSum {
    public static class Solution {
        public int[] twoSum(int[] nums, int target) {
            HashMap<Integer, Integer> map = new HashMap<>();
            int i = 0;
            for (; i < nums.length; i++) {
                if (map.containsKey(target - nums[i])) break;
                else map.put(nums[i], i);
            }
            return nums.length == 0 ? new int[]{0, 0} : new int[]{map.get(target - nums[i]), i};
        }
    }
}
