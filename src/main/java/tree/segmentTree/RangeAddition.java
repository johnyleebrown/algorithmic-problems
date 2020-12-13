package tree.segmentTree;

/**
 * 370
 *
 * ======
 *
 * Assume you have an array of length n initialized with all 0's and are given k update operations.
 *
 * Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each
 * element of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.
 *
 * Return the modified array after all k operations were executed.
 *
 * Example:
 *
 * Input: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
 * Output: [-2,0,3,5,3]
 * Explanation:
 *
 * Initial state:
 * [0,0,0,0,0]
 *
 * After applying operation [1,3,2]:
 * [0,2,2,2,0]
 *
 * After applying operation [2,4,3]:
 * [0,2,5,5,3]
 *
 * After applying operation [0,2,-2]:
 * [-2,0,3,5,3]
 *
 * ======
 *
 * https://leetcode.com/problems/range-addition/
 */
public class RangeAddition {
	/**
	 * Sum Segment Tree.
	 */
	public static class Solution {
		public int[] getModifiedArray(int n, int[][] updates) {
			SegmentTree st = new SegmentTree(n);
			for (int[] u : updates) {
				st.increment(u[0], u[1], u[2]);
			}
			int[] ans = new int[n];
			for (int i = 0; i < n; i++) {
				ans[i] = st.sum(i, i);
			}
			return ans;
		}

		private class SegmentTree {

			private int n;
			private int[] lo, hi, val, delta;

			public SegmentTree(int n) {
				this.n = n;
				lo = new int[4 * n + 1];
				hi = new int[4 * n + 1];
				val = new int[4 * n + 1];
				delta = new int[4 * n + 1];

				init(1, 0, n - 1);
			}

			// fill in lo and hi
			private void init(int i, int curLo, int curHi) {
				lo[i] = curLo;
				hi[i] = curHi;
				if (curLo == curHi) {
					return;
				}
				int mid = curLo + (curHi - curLo) / 2;
				init(2 * i, curLo, mid);
				init(2 * i + 1, mid + 1, curHi);
			}

			public void increment(int lo, int hi, int val) {
				increment(1, lo, hi, val);
			}

			private void increment(int i, int l, int r, int val) {

				// no cover
				if (isNotIntersecting(i, l, r)) {
					return;
				}

				// full cover
				// [l,r] is covering node at index i
				if (isCovering(i, l, r)) {
					delta[i] += val;
					return;
				}

				// partial cover
				propagate(i);

				increment(2 * i, l, r, val);
				increment(2 * i + 1, l, r, val);

				update(i);
			}

			private boolean isNotIntersecting(int i, int l, int r) {
				return r < lo[i] || l > hi[i];
			}

			private boolean isCovering(int i, int l, int r) {
				return l <= lo[i] && r >= hi[i];
			}

			private void propagate(int i) {
				delta[2 * i] += delta[i];
				delta[2 * i + 1] += delta[i];
				delta[i] = 0;
			}

			private void update(int i) {
				val[i] = delta[2 * i] + val[2 * i] + delta[2 * i + 1] + val[2 * i + 1];
			}

			public int sum(int lo, int hi) {
				return sum(1, lo, hi);
			}

			private int sum(int i, int l, int r) {

				// no cover
				if (isNotIntersecting(i, l, r)) {
					return 0;
				}

				// full cover
				// [l,r] is covering node at index i
				if (isCovering(i, l, r)) {
					return delta[i] + val[i];
				}

				// partial cover
				propagate(i);

				int left = sum(2 * i, l, r);
				int right = sum(2 * i + 1, l, r);

				update(i);

				return left + right;
			}
		}
	}
}