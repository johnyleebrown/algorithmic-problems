package util.contest;

import util.tester.Tester;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * $INSERT_PROBLEM_NUMBER
 *
 * ======
 *
 * Task.
 *
 * $INSERT_TASK
 *
 * ======
 *
 * Similar: $INSERT_SIMILAR.
 *
 * ======
 *
 * Source: Leetcode
 */
public class FindTheDistanceValueBetweenTwoArrays
{
	/**
	 * $INSERT_EXPLANATION.
	 */
	public static class Solution
	{
		public int findTheDistanceValue(int[] a, int[] b, int d) {
			TreeSet<Integer> t = new TreeSet<>();
			for (int i = 0; i < b.length; i++) {
				t.add(b[i]);
			}
			int res = 0;
			for (int i = 0; i < a.length; i++) {
				if (t.ceiling(a[i] + d) == null && t.floor(a[i] - d) == null) {
					res++;
				}
			}
			return res;
		}

        public Solution(){}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add(new int[]{4,5,8}, new int[]{10,9,1,8}, 2).expect(2)
				.run();
	}
}
