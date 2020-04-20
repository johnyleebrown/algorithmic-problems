package regular.array;

/**
 * 485
 */
public class MaxConsecutiveOnes {
    public static class Solution {
        public int findMaxConsecutiveOnes(int[] nums) {
            int res = 0;
            int localCount = 0;

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 1) {
                    localCount++;
                } else {
                    res = Math.max(res, localCount);
                    localCount = 0;
                }
            }

            return Math.max(res, localCount);
        }
    }
}