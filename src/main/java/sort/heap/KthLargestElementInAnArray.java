package sort.heap;

/**
 * 215
 */
public class KthLargestElementInAnArray {

	/**
	 * Keep a heap with the smallest element at 1st position, first swim k
	 * elements, then sink others.
	 */
	public static class Solution {

		public int findKthLargest(int[] nums, int k) {
			int[] temp = new int[k + 1];
			for (int i = 0; i < k; i++) {
				temp[i + 1] = nums[i];
				swim(temp, i + 1);
			}

			for (int i = k; i < nums.length; i++) {
				if (temp[1] < nums[i]) {
					temp[1] = nums[i];
					sink(temp, k + 1);
				}
			}

			return temp[1];
		}

		private void swim(int[] a, int i) {
			while (i > 1 && a[i] < a[i / 2]) {
				exch(a, i, i / 2);
				i = i / 2;
			}
		}

		private void sink(int[] a, int k) {
			int i = 1;
			while (i * 2 < k) {
				int j = i * 2;

				if (j + 1 < k && a[j + 1] < a[j]) {
					j++;
				}

				if (a[j] > a[i]) {
					break;
				}

				exch(a, i, j);
				i = j;
			}
		}

		private void exch(int[] a, int i, int j) {
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}

	public static class Solution2 {

		public int findKthLargest(int[] nums, int k) {
			int n = nums.length;
			if (n == 0) {
				return -1;
			}
			k = n - k;
			int lo = 0;
			int hi = n - 1;
			while (lo < hi) {
				int p = part(lo, hi, nums);
				if (p == k) {
					return nums[p];
				} else if (p > k) {
					hi = p - 1;
				} else {
					lo = p + 1;
				}
			}
			return nums[lo];
		}

		private int part(int lo, int hi, int[] a) {
			int i = lo;
			int j = hi + 1;
			int v = a[lo];
			while (true) {
				while (a[++i] < v) {
					if (i == hi) {
						break;
					}
				}

				while (a[--j] > v) {
					if (j == lo) {
						break;
					}
				}

				if (i >= j) {
					break;
				}

				exch(i, j, a);
			}
			exch(lo, j, a);
			return j;
		}

		private void exch(int i, int j, int[] a) {
			int t = a[i];
			a[i] = a[j];
			a[j] = t;
		}
	}
}