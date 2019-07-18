package Easy.Array;

/**
 * 674
 * Given an unsorted array of integers, find the length of longest continuous increasing subsequence.
 */
public class LongestContinuousIncreasingSubsequence {
    // O(n)
    class Solution {
        public int findLengthOfLCIS(int[] nums) {
            int res = 0, cnt = 0;
            for (int i = 0; i < nums.length; i++) {
                if (i == 0 || nums[i - 1] < nums[i]) res = Math.max(res, ++cnt);
                else cnt = 1;
            }
            return res;
        }
    }
}
