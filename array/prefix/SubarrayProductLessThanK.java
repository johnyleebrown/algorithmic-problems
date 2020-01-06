package prefix;

/**
 * 713
 * Your are given an array of positive integers nums.
 * Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.
 */
public class SubarrayProductLessThanK {

    class Solution {
        public int numSubarrayProductLessThanK(int[] nums, int k) {
            if (k == 0) return 0;
            double logk = Math.log(k);
            double[] prefix = new double[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                prefix[i+1] = prefix[i] + Math.log(nums[i]);
            }

            int ans = 0;
            for (int i = 0; i < prefix.length; i++) {
                int lo = i + 1, hi = prefix.length;
                while (lo < hi) {
                    int mi = lo + (hi - lo) / 2;
                    if (prefix[mi] < prefix[i] + logk - 1e-9) lo = mi + 1;
                    else hi = mi;
                }
                ans += lo - i - 1;
            }
            return ans;
        }
    }


    class Solution2 {
        public int numSubarrayProductLessThanK(int[] nums, int k) {
            if (k <= 1) return 0;
            int prod = 1, ans = 0, left = 0;
            for (int right = 0; right < nums.length; right++) {
                prod *= nums[right];
                while (prod >= k) prod /= nums[left++];
                ans += right - left + 1;
            }
            return ans;
        }
    }
}
