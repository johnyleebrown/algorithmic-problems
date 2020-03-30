package dp._2_Multidimensional.circular;

import util.tester.Tester;

/**
 * 1388
 *
 * ======
 *
 * Task.
 *
 * There is a pizza with 3n slices of varying size, you and your friends will
 * take slices of pizza as follows:
 *
 * You will pick any pizza slice. Your friend Alice will pick next slice in anti
 * clockwise direction of your pick. Your friend Bob will pick next slice in
 * clockwise direction of your pick. Repeat until there are no more slices of
 * pizzas.
 *
 * Sizes of Pizza slices is represented by circular array slices in clockwise
 * direction.
 *
 * Return the maximum possible sum of slice sizes which you can have.
 *
 * ======
 *
 * Similar: HRII.
 *
 * ======
 *
 * Source: Leetcode
 */
public class PizzaWith3nSlices {
	/**
	 * We can use either 0 or n-1 th. Same idea as HRII.
	 */
	public static class Solution {
		public int maxSizeSlices(int[] slices) {
						
		}
	}

	public static void main(String[] args) {
		new Tester(new Solution())
				.add(new int[]{1, 2, 3, 4, 5, 6}).expect(10)
				.run();
	}
}
