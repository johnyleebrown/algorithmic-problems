package string.subsequence;

import java.util.Arrays;
import java.util.Random;

/**
 * 792
 */
public class NumberOfMatchingSubsequences {
	class Solution {
		/**
		 * the idea is to have a prefix sums array.
		 * and when we randomly pick a value, we will look up with bs a range of the indexes for it.
		 */
		int[] pre;
		int n;
		Random r;

		public Solution(int[] w) {
			r = new Random();
			n = w.length;
			pre = new int[n];
			pre[0]=w[0];
			for (int i = 1; i < n; i++) {
				pre[i] = pre[i - 1] + w[i];
			}
			Arrays.binarySearch(pre, 1);
		}

		public int pickIndex() {
			int x = r.nextInt(pre[n - 1] + 1);
			int ind = bs(x);
			return pre[ind] == x ? ind : ind + 1;
		}

		private int bs(int t) {
			int lo = 0;
			int hi = n - 1;
			while (lo <= hi) {
				int mid = lo + (hi - lo) / 2;
				if (pre[mid] == t) {
					return mid;
				} else if (pre[mid] > t) {
					hi = mid - 1;
				} else {
					lo = mid + 1;
				}
			}
			return lo;
		}
	}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
}
