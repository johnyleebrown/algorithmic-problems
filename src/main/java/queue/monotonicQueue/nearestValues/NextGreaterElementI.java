package queue.monotonicQueue.nearestValues;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 496
 */
public class NextGreaterElementI {

	private static class Solution1 {

		private Map<Integer, Integer> m = new HashMap<>(); // value, index of nums1

		public int[] nextGreaterElement(int[] nums1, int[] nums2) {
			for (int i = 0; i < nums1.length; i++) {
				m.put(nums1[i], i);
			}

			DecreasingMQ q = new DecreasingMQ(-1, nums1.length);
			for (int i = nums2.length - 1; i >= 0; i--) {
				q.push(nums2[i]);
			}

			return q.getNearestValues();
		}

		/**
		 * Decreasing Nearest Values MQ
		 */
		private class DecreasingMQ {

			private Deque<Integer> q;
			private int defaultNextValue;
			private int[] nearestValues;

			public DecreasingMQ(int defaultNextValue, int n) {
				q = new ArrayDeque<>();
				nearestValues = new int[n];
				this.defaultNextValue = defaultNextValue;
			}

			public void push(int val) {
				while (!q.isEmpty() && val >= q.peekLast()) {
					q.removeLast();
				}

				customNearestValuesUpdate(val);

				q.addLast(val);
			}

			private void customNearestValuesUpdate(int val) {
				if (m.containsKey(val)) {
					if (q.isEmpty()) {
						nearestValues[m.get(val)] = defaultNextValue;
					} else {
						nearestValues[m.get(val)] = q.peekLast();
					}
				}
			}

			public int[] getNearestValues() {
				return nearestValues;
			}
		}
	}
}