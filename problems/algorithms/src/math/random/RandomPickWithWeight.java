package math.random;

import java.util.Random;
import java.util.TreeMap;

/**
 * 528
 *
 * ======
 *
 * Task.
 *
 * Given an array w of positive integers, where w[i] describes the weight of
 * index i, write a function pickIndex which randomly picks an index in
 * proportion to its weight.
 */
public class RandomPickWithWeight {
	/**
	 * Time complexity: O(n) Space complexity: O(n)
	 */
	class Solution {
		TreeMap<Integer, Integer> map;
		Random rand;
		int sum = 0;

		public Solution(int[] w) {
			rand = new Random();
			map = new TreeMap<>();
			for (int i = 0; i < w.length; i++) {
				map.put(sum + w[i] - 1, i);
				sum += w[i];
			}
		}

		public int pickIndex() {
			return map.get(map.ceilingKey(rand.nextInt(sum)));
		}
	}
}
