package Easy.DP;

/**
 * 303
 * Given an integer array nums,
 * find the sum of the elements between
 * indices i and j (i ≤ j), inclusive.
 */
public class RangeSumQueryImmutable {

    // sum from i to j equals sum from 0 to j - sum from 0 to i, i < j
    // O(1) per query, O(n) pre computation
    // space: O(n)
    private class NumArray{
        private int[] sum;

        public NumArray(int[] nums) {
            sum = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++)
                sum[i + 1] = sum[i] + nums[i];
        }

        public int sumRange(int i, int j) {
            return sum[j + 1] - sum[i];
        }
    }
}
