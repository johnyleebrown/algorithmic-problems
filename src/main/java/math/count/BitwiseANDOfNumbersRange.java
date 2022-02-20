package math.count;

/**
 * 1131
 */
public class BitwiseANDOfNumbersRange {

	/**
	 * @notion
	 */
	public static class Solution {

		public int maxAbsValExpr(int[] a, int[] b) {
			Ans ans1 = new Ans(), ans2 = new Ans(), ans3 = new Ans(), ans4 = new Ans();
			for (int i = 0; i < a.length; i++) {
				ans1.update(a[i] + b[i] - i, a[i] + b[i] - i);
				ans2.update(a[i] - b[i] - i, a[i] - b[i] - i);
				ans3.update(a[i] - b[i] + i, a[i] - b[i] + i);
				ans4.update(a[i] + b[i] + i, a[i] + b[i] + i);
			}
			return Math.max(Math.max(ans1.getDiff(), ans2.getDiff()),
					Math.max(ans3.getDiff(), ans4.getDiff()));
		}

		static class Ans {

			int min, max;

			Ans() {
				min = Integer.MAX_VALUE;
				max = Integer.MIN_VALUE;
			}

			void update(int candidateMax, int candidateMin) {
				updateMax(candidateMax);
				updateMin(candidateMin);
			}

			void updateMax(int candidate) {
				max = Math.max(max, candidate);
			}

			void updateMin(int candidate) {
				min = Math.min(min, candidate);
			}

			int getDiff() {
				return max - min;
			}
		}
	}
}
