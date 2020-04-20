package a0.array.regular;

import java.util.HashMap;
import java.util.Map;

/**
 * 1
 * Company: Yelp
 *
 * Given an Array of integers, return indices of the
 * two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one
 * solution, and you may not use the same element twice.
 */

public class TwoSum {

    /**
     * Beats 92% of java submissions.
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int i = 0;
        for ( ; i < nums.length ; i++){
            if (map.containsKey(target-nums[i])) break;
            else map.put(nums[i],i);
        }
        return nums.length == 0 ? new int[]{0,0} : new int[]{map.get(target-nums[i]),i};
    }

    /**
     * Provided solution
     *
     * Time complexity : O(n).
     * We traverse the list containing n elements only once.
     * Each look up in the table costs only O(1) time.
     *
     * Space complexity : O(n).
     * The extra space required depends on the number of
     * items stored in the hash table, which stores at most n elements.
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
