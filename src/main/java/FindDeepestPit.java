/**
 * @company TikTok
 * @desc Find Deepest Pit
 * You have an array of heights of the mountain, find the maximum depth in that mountain.
 */
public class FindDeepestPit {

	public static void main(String[] args) {
		// basic
		System.out.println(new Solution1().solve(new int[]{10, 0, 10}) == 10);
		System.out.println(new Solution1().solve(new int[]{100, 0, 10, 10, 100}) == 100);
		System.out.println(new Solution1().solve(new int[]{100, 0, 10, -50, 0}) == 50);

		// no pit
		System.out.println(new Solution1().solve(new int[]{100, 200}) == -1);
		System.out.println(new Solution1().solve(new int[]{100}) == -1);
		System.out.println(new Solution1().solve(new int[]{100, 50}) == -1);
		System.out.println(new Solution1().solve(new int[]{100, 50, 0}) == -1);
		System.out.println(new Solution1().solve(new int[]{0, 50}) == -1);

		// easy pit
		System.out.println(new Solution1().solve(new int[]{30, 0, 40}) == 30);
		System.out.println(new Solution1().solve(new int[]{30, 0, 20}) == 20);

		// medium
		System.out.println(new Solution1().solve(new int[]{60, 30, 0, 20}) == 20);
		System.out.println(new Solution1().solve(new int[]{60, 30, 0, 50}) == 50);
		System.out.println(new Solution1().solve(new int[]{60, 30, 0, -30}) == -1);
		System.out.println(new Solution1().solve(new int[]{60, 30, 0, 100}) == 60);

		// hard
		System.out.println(new Solution1().solve(new int[]{5, 0, 5, 100, 50, 100}) == 50);
		System.out.println(new Solution1().solve(new int[]{40, 20, 0, 30, 10, 20}) == 30);
		System.out.println(new Solution1().solve(new int[]{40, 20, 0, 30, 20, 30}) == 30);
		System.out.println(new Solution1().solve(new int[]{40, 20, 0, 30, 20, 35}) == 35);
		System.out.println(new Solution1().solve(new int[]{40, 20, 0, 30, -20, 20}) == 40);
		System.out.println(
				new Solution1().solve(new int[]{40, 20, 0, 30, -20, 20, 20, 10}) == 40);
		System.out.println(new Solution1().solve(new int[]{40, 20, 0, 30, -20, 30}) == 50);
		System.out.println(new Solution1().solve(new int[]{40, 20, 0, 30, -20, 35}) == 55);
		System.out.println(
				new Solution1().solve(new int[]{40, 20, 0, 30, 50, 45, 45, 50}) == 40);
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
			return q.deepest == Integer.MIN_VALUE ? -1 : q.deepest;
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
		}
	}
}
