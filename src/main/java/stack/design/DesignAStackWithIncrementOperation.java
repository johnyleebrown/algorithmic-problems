package stack.design;

/**
 * 1381
 */
public class DesignAStackWithIncrementOperation {

	/**
	 * Using deque. Increment takes O(k). Pop, push O(1);
	 */
	public static class Solution1 {

		/**
		 * Lazy increment i.e. increment when we want to get a value. All ops
		 * are O(1). Space: O(n*2).
		 *
		 * Keep increments separately. When we pop - add inc for n, and inc[n-1]
		 * by current inc.
		 */
		public static class Solution2 {

			class CustomStack {

				private int max;
				private int n;
				private int[] inc;
				private int[] vals;

				public CustomStack(int max) {
					this.max = max;
					inc = new int[max];
					vals = new int[max];
				}

				public void push(int x) {
					if (n == max)
						return;
					vals[n++] = x;
				}

				public int pop() {
					if (n <= 0)
						return -1;
					int curInc = inc[n - 1];
					int ret = vals[n - 1] + curInc;
					vals[n - 1] = inc[n - 1] = 0;
					n--;
					if (n - 1 >= 0)
						inc[n - 1] += curInc;
					return ret;
				}

				public void increment(int k, int val) {
					int ind = Math.min(k, n) - 1;
					if (ind >= 0)
						inc[ind] += val;
				}
			}
		}

		/**
		 * Optimized Space: O(n).
		 */
		public static class Solution3 {

			class CustomStack {

				private int[] inc;
				private int max;
				private int n;
				private int lastNumber;

				public CustomStack(int max) {
					this.max = max;
					inc = new int[max];
				}

				public void push(int x) {
					if (n == max)
						return;
					inc[n++] = x - lastNumber;
					lastNumber = x;
				}

				public int pop() {
					if (n == 0)
						return -1;
					int ret = lastNumber;
					lastNumber -= inc[--n];
					return ret;
				}

				public void increment(int k, int val) {
					inc[0] += val;
					lastNumber += val;
					if (k < n) {
						inc[k] -= val;
						lastNumber -= val;
					}
				}
			}
		}

		class CustomStack {

			int[] vals;
			int n;
			int max;

			public CustomStack(int m) {
				max = m;
				vals = new int[m];
			}

			public void push(int x) {
				if (n == max)
					return;
				vals[n] = x;
				n++;
			}

			public int pop() {
				if (n > 0) {
					int ret = vals[n - 1];
					vals[n - 1] = 0;
					n--;
					return ret;
				}

				return -1;
			}

			public void increment(int k, int val) {
				int hit = Math.min(k, n);
				while (--hit >= 0) {
					vals[hit] = vals[hit] + val;
				}
			}
		}
	}
}
