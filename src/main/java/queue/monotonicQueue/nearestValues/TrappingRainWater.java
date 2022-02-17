package queue.monotonicQueue.nearestValues;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 42
 */
public class TrappingRainWater {

	/**
	 * Nearest elements.
	 *
	 * The water can be trapped if the bars form a container. We need left,
	 * bottom and right; edges(left and right) should be bigger than bottom. We
	 * will be using a decreasing monotone queue to keep indexes of bars.
	 *
	 * https://en.wikipedia.org/wiki/Liebig%27s_law_of_the_minimum
	 */
	public static class Solution {

		public int trap(int[] height) {
			MQ q = new MQ();
			for (int i = 0; i < height.length; i++) {
				q.push(new Item(height[i], i));
			}
			return q.totalTrappedWater;
		}

		private static class MQ {

			public int totalTrappedWater;
			private Deque<Item> q = new ArrayDeque<>();

			public void push(Item newItem) {
				while (!q.isEmpty() && q.peekLast().val < newItem.val) {
					Item bottom = q.removeLast();

					// we need a left barrier to compute area
					// otherwise we can't form a container
					if (!q.isEmpty()) {
						int leftBarrierIndex = q.peekLast().ind;
						int containerWidth = newItem.ind - leftBarrierIndex - 1;

						int containerUpperBoundary = Math.min(q.peekLast().val, newItem.val);
						int containerLowerBoundary = bottom.val;
						int containerHeight = containerUpperBoundary - containerLowerBoundary;

						int area = containerHeight * containerWidth;
						totalTrappedWater += area;
					}
				}
				q.addLast(newItem);
			}
		}

		private static class Item {

			int val, ind;

			Item(int val, int ind) {
				this.val = val;
				this.ind = ind;
			}
		}
	}

	/**
	 * Nearest highest elements can hold water, so we need to find them. Find
	 * the highest elevation first. Then on the left and right count the
	 * differences between highest and smaller elevations that go after it.
	 */
	public class Solution2 {

		public int trap(int[] height) {
			if (height.length <= 2) {
				return 0;
			}

			// find the highest elevation
			int max = Integer.MIN_VALUE;
			int maxIndex = -1;
			for (int i = 0; i < height.length; i++) {
				if (height[i] > max) {
					max = height[i];
					maxIndex = i;
				}
			}

			int leftMax = height[0];
			int water = 0;
			for (int i = 1; i < maxIndex; i++) {
				// update highest on the left
				if (height[i] > leftMax) {
					leftMax = height[i];
				}
				// until the next highest we will count the diff
				// compared to the previous highest
				else {
					water += leftMax - height[i];
				}
			}

			int rightMax = height[height.length - 1];
			for (int i = height.length - 2; i > maxIndex; i--) {
				if (height[i] > rightMax) {
					rightMax = height[i];
				} else {
					water += rightMax - height[i];
				}
			}

			return water;
		}
	}
}
