package queue.monotonicQueue.nearestValues;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 221
 */
public class MaximalSquare {

	/**
	 * Same as 85.
	 */
	class Solution {

		public int maximalSquare(char[][] matrix) {
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
			MQ q = new MQ();
			for (int i = 0; i <= heights.length; i++) {
				int currentValue = i != heights.length ? heights[i] : 0;
				q.push(new Item(currentValue, i));
			}
			return q.maxArea;
		}

		private class MQ {

			private Deque<Item> q = new ArrayDeque<>();
			private int maxArea;

			public void push(Item newItem) {
				while (!q.isEmpty() && newItem.val < q.peekLast().val) {
					Item upperBoundary = q.removeLast();
					int leftBoundaryIndex = -1;
					if (!q.isEmpty()) {
						leftBoundaryIndex = q.peekLast().ind;
					}
					int width = newItem.ind - leftBoundaryIndex - 1;
					int side = Math.min(width, upperBoundary.val);
					int currentArea = side * side;

					maxArea = Math.max(maxArea, currentArea);
				}

				q.addLast(newItem);
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
