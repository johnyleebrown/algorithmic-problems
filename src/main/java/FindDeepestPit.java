import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @company TikTok
 * @desc Find Deepest Pit
 * You have an array of heights of the mountain, find the maximum depth in that mountain.
 */
public class FindDeepestPit {

	public static void main(String[] args) {
		int res = new Solution1().solve(new int[]{10, 0, 10});
		System.out.println(res == 10);
		int res2 = new Solution1().solve(new int[]{100, 0, 10, 10, 100});
		System.out.println(res2 == 100);
		int res3 = new Solution1().solve(new int[]{100, 0, 10, -50, 0});
		System.out.println(res3 == 50);
	}

	/**
	 * Monotonic queue with types
	 * 3 types: left, bottom, right - in that order
	 * bigger left would remove everything to the left
	 * deeper bottom would remove everything until left
	 * bigger right would remove everything until bottom
	 */
	public static class Solution1 {

		public int solve(int[] heights) {
			MQ q = new MQ();
			for (int i = 0; i < heights.length; i++) {
				q.add(i, heights[i]);
			}
			return q.deepest;
		}

		class MQ {

			Deque<Height> q;
			Height maxLeft, minBottom, maxRight;
			int deepest;

			MQ() {
				q = new ArrayDeque<>();
				deepest = 0;
			}

			void add(int ind, int val) {
				if (maxLeft != null && val > maxLeft.value) {

					maxLeft = new Height(0, ind, val);
				}
				if (maxLeft == null || minBottom == null || maxRight == null) {
					if (maxLeft == null) {
						maxLeft = new Height(0, ind, val);
					} else if (minBottom == null && val < maxLeft.value) {
						minBottom = new Height(1, ind, val);
					} else if (maxRight == null && minBottom != null && val > minBottom.value) {
						maxRight = new Height(2, ind, val);
						deepest = Math.min(maxLeft.value, maxRight.value) - minBottom.value;
					}
				}

			}
		}

		class Height {

			int type; // 0,1,2
			int ind;
			int value;

			public Height(int type, int ind, int value) {
				this.type = type;
				this.ind = ind;
				this.value = value;
			}
		}
	}

	public static class Solution2 {

	}
}
