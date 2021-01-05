package dp.subsequences.lis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 801
 */
public class MinimumSwapsToMakeSequencesIncreasing {
	public static void main(String[] args) {
		int[] a = new int[]{0, 7, 8, 10, 10, 11, 12, 13, 19, 18};
		int[] b = new int[]{4, 4, 5, 7, 11, 14, 15, 16, 17, 20};
		new MinimumSwapsToMakeSequencesIncreasing.Solution().minSwap(a, b);
	}

	public static class Solution {

		int ans = Integer.MAX_VALUE;
		List<String> st = new ArrayList<>();

		public int minSwap(int[] a, int[] b) {
			// subproblem = how many swaps need to be done in [0..i]
			// action = either we swap or not
			// cache =
			back(0, 0, a, b);
			System.out.println("=============");
			System.out.println(ans);
			System.out.println("=============");
			return ans;
		}

		boolean goodToSwap(int i, int[] a, int[] b) {
			return (i == 0 || (a[i - 1] < b[i] && b[i - 1] < a[i]));
		}

		void back(int k, int count, int[] a, int[] b) {
			if (k == a.length) {
				ans = Math.min(ans, count);
				System.out.println("count " + count);
				System.out.println("a " + Arrays.toString(a));
				System.out.println("b " + Arrays.toString(b));
				for (String ss : st) {
					System.out.println(ss);
				}
				System.out.println("=============");
			} else {
//				for (int k = k; k < a.length; k++) {
				if (a[k] == b[k]) {
					st.add("a[i] == b[i] ==> i " + k);
					back(k + 1, count, a, b);
					st.remove(st.size() - 1);
				} else if (k == 0 || (a[k - 1] < b[k] && b[k - 1] < a[k])) {
					st.add("good ==> i " + k);
					int t = a[k];
					a[k] = b[k];
					b[k] = t;
					back(k + 1, count + 1, a, b);
					t = a[k];
					a[k] = b[k];
					b[k] = t;
					st.remove(st.size() - 1);
				} else if (a[k - 1] >= a[k] || b[k - 1] >= b[k]) {
					System.out.println("bad ==> i " + k);
					return;
				} else {
					st.add("else ==> i " + k);
					back(k + 1, count, a, b);
					st.remove(st.size() - 1);
				}
//				}
			}
		}
	}
}
