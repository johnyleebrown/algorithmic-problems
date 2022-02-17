package queue.monotonicQueue.nearestValues;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 456
 */
public class A132Pattern {

	/**
	 * The idea is to keep decreasing MQ and record the element that we are
	 * going to remove for the purpose of comparison of it with other smaller
	 * elements.
	 */
	public static class Solution {

		public boolean find132pattern(int[] a) {
			MQ queue = new MQ();

			for (int i = a.length - 1; i >= 0; i--) {
				if (queue.push(a[i])) {
					return true;
				}
			}

			return false;
		}

		private class MQ {

			private Deque<Integer> queue;
			private int prev;

			private MQ() {
				queue = new ArrayDeque<>();
				prev = Integer.MIN_VALUE;
			}

			public boolean push(int cur) {
				if (prev > cur) {
					return true;
				}

				while (!queue.isEmpty() && queue.peekLast() < cur) {
					prev = queue.peekLast();
					queue.removeLast();
				}

				queue.addLast(cur);
				return false;
			}
		}
	}
}