package array.other;

import java.util.Arrays;

/**
 * 136
 *
 * ======
 *
 * Task.
 *
 * Given a non-empty array of integers, every element appears twice except for
 * one. Find that single one.
 *
 * ======
 *
 * Source: Leetcode
 */
public class SingleNumber {
	public static class Solution {
		public int singleNumber(int[] nums) {
			int x = nums[0];
			for (int i = 1; i < nums.length; i++) {
				x ^= nums[i];
			}
			return x;
		}
	}

	/**
	 * TODO
	 * Divide and conquer
	 * https://leetcode.com/problems/single-number/discuss/158468/Guide%3A-The-Divide-and-Conquer-Approach-O(1)-extra-space-O(n)-time
	 */
	public static class Solution2 {
		public int singleNumber(int[] a) {
			int x = search(0, a.length - 1, a);
			System.out.println(x);
			return a[x];
		}
		int search(int lo, int hi, int[] a) {

			int p = partition(lo, hi, a);
			int sameOnLeft=(p-1>=lo&&a[p-1]==a[p]?1:0);
			int smaller = p - lo - sameOnLeft;
			int sameOnRight=(p+1<=hi&&a[p+1]==a[p]?1:0);
			int bigger = hi - p - sameOnRight;

			System.out.println(Arrays.toString(a));
			System.out.println(lo+","+hi+","+p);

			if (smaller%2!=0) { // go left
				return search(lo,p-(1-sameOnLeft),a);
			}
			else if (bigger%2!=0) { // go right
				return search(p+(1-sameOnRight),hi,a);
			}

			return p;
		}
		int partition(int lo, int hi, int[] a) {
			System.out.println("-> "+lo+","+hi);
			int v = a[lo];
			int i = lo;
			int j = hi + 1;
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

				if (i>=j) {
					break;
				}

				swap(i, j, a);
			}

			swap(lo, j, a);
			return j;
		}
		void swap(int i, int j, int[] a) {
			int t = a[i];
			a[i] = a[j];
			a[j] = t;
		}
	}
}