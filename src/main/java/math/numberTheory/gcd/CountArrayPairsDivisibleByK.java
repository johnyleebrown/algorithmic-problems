package math.numberTheory.gcd;

import java.util.HashMap;
import java.util.Map;

/**
 * 2183
 */
public class CountArrayPairsDivisibleByK {

	/**
	 * number a is divisible by b if gcd(a,b) = b
	 * gcd(a*b, k) = k => gcd(a,k) * gcd(b,k) % k = 0
	 * so let's add all gcd to map and check if gcd(a) * gcd(b) % k == 0
	 * we will check with all the previous indexes before current index since those are the
	 * only interest for us
	 */
	public static class Solution {

		public long countPairs(int[] nums, int k) {
			Map<Long, Long> gcdMap = new HashMap<>();
			long ans = 0;

			for (int num : nums) {
				long x = gcd(num, k);
				for (long y : gcdMap.keySet()) {
					if (x * y % k == 0) {
						ans += gcdMap.get(y);
					}
				}
				gcdMap.put(x, gcdMap.getOrDefault(x, 0L) + 1L);
			}

			return ans;
		}

		int gcd(int a, int b) {
			if (b == 0) return a;
			else return gcd(b, a % b);
		}
	}
}
