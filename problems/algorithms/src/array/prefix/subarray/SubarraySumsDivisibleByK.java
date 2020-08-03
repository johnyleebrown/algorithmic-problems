package array.prefix.subarray;

import java.util.HashMap;
import java.util.Map;

/**
 * 974
 */
public class SubarraySumsDivisibleByK {
	/**
	 * About the problems - sum of contiguous subarray, prefix sum is a common
	 * technique.
	 *
	 * If we look at 560, to find if [l,r] sum == k, we check if we have
	 * prefix sum = cursum-k. Here though we check if we have in map such
	 * prefix sum so prefixsum%k==cursum%k. Because if sum[0, i] % K == sum[0,
	 * j] % K, sum[i + 1, j] is divisible by by K.
	 */
	public static class Solution {
		public int subarraysDivByK(int[] A, int K) {
			Map<Integer, Integer> map = new HashMap<>();
			map.put(0, 1);
			int count = 0, sum = 0;
			for (int a : A) {
				sum = (sum + a) % K;
				// Because -1 % 5 = -1, but we need the positive mod 4
				if (sum < 0) {
					sum += K;
				}
				count += map.getOrDefault(sum, 0);
				map.put(sum, map.getOrDefault(sum, 0) + 1);
			}
			return count;
		}
	}
}