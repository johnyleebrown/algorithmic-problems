package recursion.other;


import java.util.Arrays;

/**
 * 698
 * Partition to K Equal Sum Subsets
 *
 * Given an array of integers nums and a positive integer k,
 * find whether it's possible to divide this array into k
 * non-empty subsets whose sums are all equal.
 *
 * Example 1:
 *
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * Output: True
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 *
 * Note:
 *
 *     1 <= k <= len(nums) <= 16.
 *     0 < nums[i] < 10000.
 */
public class PartitionToKEqualSumSubsets
{
    class Solution {
        public boolean canPartitionKSubsets(int[] nums, int k)
        {
            int sum = Arrays.stream(nums).sum();
            if (sum % k != 0) return false;
            return dfs(new boolean[nums.length], nums, 0, k, sum / k, 0);
        }

        private boolean dfs(boolean[] used, int[] nums, int curSum, int subsetNum, int maxSum, int currentIndex)
        {
            // if we have found all the subsets
            if (subsetNum == 0)
            {
                return true;
            }

            // if if found 1 subset correctly
            if (curSum == maxSum)
            {
                // cursum is now zero as we start over
                return dfs(used, nums, 0, subsetNum - 1, maxSum, 0);
            }

            // go through each number in the array to find the right one
            for (int i = currentIndex; i < nums.length; i++)
            {
                // we can't take the ones that were already used previously
                if (!used[i])
                {
                    // mark it as used so when in the recursion we won't take it
                    used[i] = true;
                    if (dfs(used, nums, curSum + nums[i], subsetNum, maxSum, i + 1))
                    {
                        return true;
                    }
                    // if the number isn't right for us, we mark it as not used
                    used[i] = false;
                }
            }

            return false;
        }
    }
}
