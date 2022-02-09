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
			MQ q = new MQ(heights);
			for (int i = 1; i < heights.length; i++) {
				q.add(i, heights[i]);
			}
			return q.deepest;
		}

		class MQ {

			Height left, floor, right;
			int deepest;

			MQ(int[] heights) {
				deepest = Integer.MIN_VALUE;
				left = new Height(0, 0, heights[0]);
			}

			void add(int ind, int val) {
				if (val > left.value) {
					updateMax(val);
					left = new Height(0, ind, val);
					floor = null;
					right = null;
				} else if (floor == null || val < floor.value) {
					floor = new Height(1, ind, val);
				} else {
					right = new Height(2, ind, val);
					updateMax(right.value);
				}
			}

			void updateMax(int val) {
				if (floor != null) {
					int diff = Math.min(left.value, val) - floor.value;
					deepest = Math.max(diff, deepest);
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

			void update(int ind, int value) {
				this.ind = ind;
				this.value = value;
			}
		}
	}

	public static class Solution2 {

	}
}
