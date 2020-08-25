package array.prefix;

import java.util.Random;

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
	 * The idea is to have a prefix sums array and when we randomly pick a
	 * value, we will look up with bs a range of the indexes for it.
	 */
	public static class Solution {
		private int[] pre;
		private int n;
		private Random r;

		public Solution(int[] w) {
			r = new Random();
			n = w.length;
			pre = new int[n];
			pre[0] = w[0];
			for (int i = 1; i < n; i++) {
				pre[i] = pre[i - 1] + w[i];
			}
		}

		public int pickIndex() {
			int x = r.nextInt(pre[n - 1]) + 1; // + 1 cuz min is 1
			return bs(x);
		}

		private int bs(int t) {
			int lo = 0;
			int hi = n - 1;
			while (lo <= hi) {
				int mid = lo + (hi - lo) / 2;
				if (pre[mid] == t) {
					return mid;
				}
				else if (pre[mid] > t) {
					hi = mid - 1;
				}
				else {
					lo = mid + 1;
				}
			}
			return lo;
		}
	}
}
