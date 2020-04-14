package array.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 870
 *
 * Given two arrays A and B of equal size, the advantage of A with respect to B
 * is the number of indices i for which A[i] > B[i]. Return any permutation of A
 * that maximizes its advantage with respect to B.
 */
public class AdvantageShuffle {
	public static class Solution {
		public int[] advantageCount(int[] A, int[] B) {
			Arrays.sort(A);
			int n = A.length;
			int[] res = new int[n];
			PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
			for (int i = 0; i < n; i++) pq.add(new int[]{B[i], i});
			int lo = 0, hi = n - 1;
			while (!pq.isEmpty()) {
				int[] cur = pq.poll();
				int idx = cur[1], val = cur[0];
				if (A[hi] > val) res[idx] = A[hi--];
				else res[idx] = A[lo++];
			}
			return res;
		}
	}
}
