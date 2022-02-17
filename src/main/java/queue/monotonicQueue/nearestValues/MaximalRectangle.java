package queue.monotonicQueue.nearestValues;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 85
 */
public class MaximalRectangle {

	/**
	 * First solve nearest values, then solve 84, then draw example, then it's
	 * pretty obvious.
	 */
	class Solution {

		public int maximalRectangle(char[][] matrix) {
			int n = matrix.length;
			if (n == 0) {
				return 0;
			}

			int m = matrix[0].length;
			int maxRectangle = 0;

			int[] accumulatedHeights = new int[m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					// reset accumulation if there is a gap in the bar
					// meaning - there is a gap in height
					if (matrix[i][j] == '0') {
						accumulatedHeights[j] = 0;
					} else {
						accumulatedHeights[j]++;
					}
				}

				maxRectangle = Math.max(maxRectangle, largestRectangleArea(accumulatedHeights));
			}

			return maxRectangle;
		}

		public int largestRectangleArea(int[] heights) {
			MQ queue = new MQ();

			for (int i = 0; i <= heights.length; i++) {
				int currentValue = i != heights.length ? heights[i] : 0;
				queue.push(new Item(currentValue, i));
			}

			return queue.maxArea;
		}

		private class MQ {

			Deque<Item> queue;
			int maxArea;

			private MQ() {
				queue = new ArrayDeque<>();
			}

			public void push(Item newItem) {
				while (!queue.isEmpty() && newItem.val < queue.peekLast().val) {
					Item upperBoundary = queue.removeLast();
					int leftBoundaryIndex = -1;
					if (!queue.isEmpty()) {
						leftBoundaryIndex = queue.peekLast().ind;
					}

					int width = newItem.ind - leftBoundaryIndex - 1;
					int currentArea = width * upperBoundary.val;
					maxArea = Math.max(maxArea, currentArea);
				}

				queue.addLast(newItem);
			}
		}

		private class Item {

			int val, ind;

			Item(int val, int ind) {
				this.val = val;
				this.ind = ind;
			}
		}
	}
}
