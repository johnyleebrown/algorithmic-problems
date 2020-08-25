package twoPointers.slidingWindow.count;

/**
 * 992
 *
 * ======
 *
 * Task.
 *
 * Given an array A of positive integers, call a (contiguous, not necessarily
 * distinct) subarray of A good if the number of different integers in that
 * subarray is exactly K. Return the number of good subarrays of A.
 */
public class SubarraysWithKDifferentIntegers {
	/**
	 * Solved as subproblem of max type of problem
	 * - LongestSubstringWithAtMostKDistinctCharacters.
	 */
	static class Solution {
		public int subarraysWithKDistinct(int[] ar, int k) {
			return subarraysWithAtMostKDistinct(ar, k) - subarraysWithAtMostKDistinct(ar, k - 1);
		}

		public int subarraysWithAtMostKDistinct(int[] ar, int k) {
			if (k == 0) {
				return 0;
			}

			int result = 0;
			int l = 0;
			int[] map = new int[ar.length + 1];
			int countUnique = 0;

			for (int r = 0; r < ar.length; r++) {
				map[ar[r]]++;
				if (map[ar[r]] == 1) {
					countUnique++;
				}

				while (countUnique > k) {
					map[ar[l]]--;
					if (map[ar[l]] == 0) {
						countUnique--;
					}

					l++;
				}

				result += r - l + 1;
			}

			return result;
		}
	}
}
