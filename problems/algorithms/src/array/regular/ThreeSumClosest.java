package array.regular;

import java.util.Arrays;

/**
 * 16
 *
 * Given an array S of n integers, find three integers in S such that the sum is
 * closest to a given number, target. Return the sum of the three integers. You
 * may assume that each input would have exactly one solution.
 *
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class ThreeSumClosest {
    /**
     * Time complexity: O(n^2)
     * Space complexity: O(1)
     */
    public static int solution(int[] nums, int target) {
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE;
        int res = Integer.MAX_VALUE;

        for (int i = 0 ; i < nums.length - 2; i++) {
            if (i == 0 || (nums[i] != nums[i - 1])) {
                int lo = i + 1;
                int hi = nums.length - 1;

                while (hi > lo) {
                    int curSum = nums[lo] + nums[hi] + nums[i];
                    // balance the sum
                    if (curSum < target)        lo++;
                    else if (curSum > target)   hi--;
                    else                        return curSum;
                    // update smallest diff and sum
                    if (diff > Math.abs(curSum - target)) {
                        diff = Math.abs(curSum - target);
                        res = curSum;
                    }
                }

            }
        }

        return res;
    }

    public static void main(String[] args) {
        /*
        [0,0,0]
        1
        [-1, 2, 1, -4]
        1
        [1,1,1,0]
        100
        [0,2,1,-3]
        1
        [-1,0,1,2,-1,-4]
        1
         */
    }
}
