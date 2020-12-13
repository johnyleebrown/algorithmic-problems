package array.prefix;

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
	 * Linear time complexity.
	 * Prefix sum idea.
	 *
	 * We know from prefix sums problems this sum[i,j] = sum[0,j]-sum[0,i-1]
	 * So to increment [i,j] by k we can increment [0,j] by k and increment [0,i-1] by -k.
	 * So that [0,i-1] will become zeroes and [i,j] will become k.
	 *
	 * If we go from the 0 index then we can translate this idea to: increment [i,n] by k and
	 * increment [j-1,n] by -k.
	 *
	 * Lets take an example where we have only 1 update: 6, [[1,3,2]]
	 * So we will set tmp[1] = 2 and tmp[3+1]=-2
	 * Then we will accumulate additions and will get: [0, 2, 2, 2, 2-2, 2-2]
	 */
	public static class Solution {
		public int[] getModifiedArray(int n, int[][] updates) {
			int[] tmp = new int[n];
			for (int[] u : updates) {
				tmp[u[0]] += u[2];
				if (u[1] + 1 < n) {
					tmp[u[1] + 1] -= u[2];
				}
			}
			int[] ans = new int[n];
			int sum = 0;
			for (int i = 0; i < n; i++) {
				sum += tmp[i];
				ans[i] = sum;
			}
			return ans;
		}
	}
}