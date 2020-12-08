package dp.linear.forward;

import java.util.Map;
import java.util.TreeMap;

/**
 * 975
 *
 * ======
 *
 * You are given an integer array A. From some starting index, you can make a series of jumps. The
 * (1st, 3rd, 5th, ...) jumps in the series are called odd-numbered jumps, and the (2nd, 4th, 6th,
 * ...) jumps in the series are called even-numbered jumps. Note that the jumps are numbered, not
 * the indices.
 *
 * You may jump forward from index i to index j (with i < j) in the following way:
 *
 * During odd-numbered jumps (i.e., jumps 1, 3, 5, ...), you jump to the index j such that A[i] <=
 * A[j] and A[j] is the smallest possible value. If there are multiple such indices j, you can only
 * jump to the smallest such index j.
 * During even-numbered jumps (i.e., jumps 2, 4, 6, ...), you jump to the index j such that A[i] >=
 * A[j] and A[j] is the largest possible value. If there are multiple such indices j, you can only
 * jump to the smallest such index j.
 * It may be the case that for some index i, there are no legal jumps.
 * A starting index is good if, starting from that index, you can reach the end of the array (index
 * A.length - 1) by jumping some number of times (possibly 0 or more than once).
 *
 * Return the number of good starting indices.
 *
 * ======
 *
 * https://leetcode.com/problems/odd-even-jump/
 */
public class OddEvenJump {
	/**
	 * We will be moving back, caching answers and at the same time calculating the final answer.
	 *
	 * To keep the needed values we will use a TreeMap because it optimal DS for operations that
	 * we need - find A[j] that is bigger or equals and A[j] should be minimum (ceiling) and find
	 * A[j] that is smaller of equals and A[j] should be maximum (floor).
	 *
	 * Now there is a question, how can I use this DS and look up both floors and ceilings, what
	 * if we have duplicates and what if we will indeed find a value that is on the left from
	 * current cell (i > j).
	 *
	 * The answer to this question actually lies in the description of the problem, we always
	 * need to step to the index that is to the right of ours (i < j). So each new step, whether
	 * it is odd or even has to be to the right, so the indexes that we will keep in the TreeMap
	 * are always the ones that we need - they are to the right from our index
	 *
	 * Also these indexes in TreeMap are the most closest to us ("you can only jump to the
	 * smallest such index j") because we will be updating our TreeMap on each step.
	 *
	 * Now the dp part. We will have odd and even arrays that contain answers for odd and even
	 * jumps. We start from the n-1 cell, it is true because the end is reachable and we want to
	 * find out where it is reachable from.
	 *
	 * We conclude that we can come to n-1 cell from different cells(remember frog jump problem?)
	 * - we can step to n-1 cell by making either odd or even jump from somewhere - this is why
	 * it's critical to keep odd and even arrays, because we assume that at each cell there could
	 * be both odd or even jumps.
	 *
	 * Lastly, the odd answer at cell i would be the the even answer at cell where we would jump,
	 * because we jump from odd cell to even cell and we make an odd step followed by an even
	 * step(odd[i] = even[ceilingEntry.getValue()];). Same goes for even (even[i] =
	 * odd[floorEntry.getValue()];).
	 */
	public static class Solution {
		boolean[] odd, even;
		TreeMap<Integer, Integer> m;

		public int oddEvenJumps(int[] ar) {

			int n = ar.length;

			odd = new boolean[n];
			even = new boolean[n];
			odd[n - 1] = even[n - 1] = true;

			m = new TreeMap<>();
			m.put(ar[n - 1], n - 1);

			int ans = 1;

			for (int i = n - 2; i >= 0; i--) {

				// each step could be either even or odd
				// so we keep track of both

				// A[i] <= A[j], A[j] = min, i < j
				Map.Entry<Integer, Integer> ceilingEntry = m.ceilingEntry(ar[i]);
				if (ceilingEntry == null) {
					odd[i] = false;
				} else {
					odd[i] = even[ceilingEntry.getValue()];
				}

				// A[i] >= A[j], A[j] = max, i < j
				Map.Entry<Integer, Integer> floorEntry = m.floorEntry(ar[i]);
				if (floorEntry == null) {
					even[i] = false;
				} else {
					even[i] = odd[floorEntry.getValue()];
				}

				// from each cell we want to start jumping
				// we add odd[i] because our 1st jump is always odd
				ans += odd[i] ? 1 : 0;

				// update treemap
				m.put(ar[i], i);
			}

			return ans;
		}
	}
}