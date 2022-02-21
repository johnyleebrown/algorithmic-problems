package math.numberTheory.gcd;

import java.util.HashMap;
import java.util.Map;

/**
 * 1497
 */
public class CheckIfArrayPairsAreDivisibleByK {

	/**
	 * Same as with gcd but with just a remainder.
	 */
	public static class Solution {

		public boolean canArrange(int[] arr, int k) {
			Map<Integer, Integer> m = new HashMap<>();
			for (int value : arr) {
				int x = value % k;
				if (x < 0) x += k;
				m.put(x, m.getOrDefault(x, 0) + 1);
			}

			if (m.containsKey(0) && m.get(0) % 2 != 0) return false;

			// remove 0 since m.get(i) - m.get(j) wont work for them
			m.remove(0);

			for (int i : m.keySet()) {
				int j = k - i;
				if (!m.containsKey(j)) {
					return false;
				}
				if (m.get(i) - m.get(j) != 0) {
					return false;
				}
			}

			return true;
		}
	}
}
