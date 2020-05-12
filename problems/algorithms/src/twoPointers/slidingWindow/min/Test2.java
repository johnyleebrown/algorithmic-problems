package twoPointers.slidingWindow.min;

/**
 * $INSERT_PROBLEM_NUMBER
 *
 * ======
 *
 * Task.
 *
 * $INSERT_TASK
 *
 * ======
 *
 * Source: Leetcode
 */
public class Test2 {
    /**
     * @formatter:off
     * $INSERT_EXPLANATION.
     * @formatter:on
     */
    public static class MinimumSizeSubarraySum {
        public static class Solution {
            public int[] solve(int s, int[] nums) {
                System.out.println(minSubArrayLen(s - 1, nums));
                System.out.println(minSubArrayLen(s, nums));
                return new int[0];
            }

            public int minSubArrayLen(int s, int[] nums) {
                int l = 0;
                int n = nums.length;
                int ans = n + 1;
                int sum = 0;
                for (int r = 0; r < n; r++) {
                    sum += nums[r];
                    while (sum > s) {
                        sum -= nums[l];
                        l++;
                    }
                    if (l > 0) {
                        ans = Math.min(ans, r - l + 2);
                    }
                }
                return ans == n + 1 ? 0 : ans;
            }
        }
    }

    public static void main(String[] args) {
        MinimumSizeSubarraySum.Solution s = new MinimumSizeSubarraySum.Solution();
        s.solve(8, new int[]{1, 4, 3, 5, 7, 1});
    }
}