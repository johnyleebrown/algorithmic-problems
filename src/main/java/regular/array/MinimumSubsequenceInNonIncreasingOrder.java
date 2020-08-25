package regular.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 1403
 *
 * ======
 *
 * Task.
 *
 * Given the array nums, obtain a subsequence of the array whose sum of elements
 * is strictly greater than the sum of the non included elements in such
 * subsequence.
 *
 * If there are multiple solutions, return the subsequence with minimum size and
 * if there still exist multiple solutions, return the subsequence with the
 * maximum total sum of all its elements. A subsequence of an array can be
 * obtained by erasing some (possibly zero) elements from the array.
 *
 * Note that the solution with the given constraints is guaranteed to be unique.
 * Also return the answer sorted in non-increasing order.
 *
 * ======
 *
 * Source: Leetcode
 */
public class MinimumSubsequenceInNonIncreasingOrder {
	public static class Solution {
		public List<Integer> minSubsequence(int[] a) {
			int n = a.length;
			Arrays.sort(a);
			int sum = 0;
			for (int value : a) {
				sum += value;
			}
			int cur = 0;
			List<Integer> res = new LinkedList<>();
			for (int i = n - 1; i >= 0; i--) {
				int x = cur + a[i];
				res.add(a[i]);
				if (x > sum - x) {
					break;
				}
				else {
					cur += a[i];
				}
			}
			return res;
		}
	}
}