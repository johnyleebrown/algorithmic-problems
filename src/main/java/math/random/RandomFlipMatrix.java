package math.random;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 881
 */
public class RandomFlipMatrix {

	/**
	 * Modern method of the Fisher–Yates shuffle idea is to gen math.random from 0 to n: m,
	 * then swap m and n, and then
	 * decrease n, repeat until n == 0
	 */
	static class Solution {

		Map<Integer, Integer> map;
		int rows, cols, total;
		Random rand;

		public Solution(int n_rows, int n_cols) {
			map = new HashMap<>();
			rand = new Random();
			rows = n_rows;
			cols = n_cols;
			total = rows * cols;
		}

		public int[] flip() {
			int r = rand.nextInt(total--); // supposedly generated index
			int x = map.getOrDefault(r,
					r); // check if we have already put something at this index
			map.put(r,
					map.getOrDefault(total, total)); // swap - put total at index that we generated
			return new int[]{x / cols, x % cols};
		}

		public void reset() {
			map.clear();
			total = rows * cols;
		}
	}
}
