package util.contest;

import util.tester.Tester;

import java.util.Comparator;
import java.util.PriorityQueue;

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
public class SortIntegersByThePowerValue
{
	/**
	 * $INSERT_EXPLANATION.
	 */
	public static class Solution
	{
		public int getKth(int lo, int hi, int k) {
			PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.comparingInt(this::get));
			for (int i = lo; i <= hi; i++)
			{
				q.add(i);
				if (q.size() > k) {
					q.poll();
				}
			}
			return q.poll();
		}

		/**
		 *     if x is even then x = x / 2
		 *     if x is odd then x = 3 * x + 1
		 */
		private int get(int x) {
			int c = 0;
			while (x != 1) {
				if (x % 2 == 0) {
					x /= 2;
				} else {
					x = 3*x + 1;
				}
				c++;
			}
			return c;
		}
        public Solution(){}
	}



	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add().expect()
				.run();
	}
}
