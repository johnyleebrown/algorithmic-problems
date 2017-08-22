package array;

import java.util.HashMap;

/**
 *
 * Given an array of integers, return indices of the
 * two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one
 * solution, and you may not use the same element twice.
 *
 */

public class TwoSum {

    // This solution beats > 92% of java submissions.
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int i = 0;
        for ( ; i < nums.length ; i++){
            if (map.containsKey(target-nums[i])) break;
            else map.put(nums[i],i);
        }
        return nums.length == 0 ? new int[]{0,0} : new int[]{map.get(target-nums[i]),i};
    }

    public static void main(String[] args) {

    }
}
