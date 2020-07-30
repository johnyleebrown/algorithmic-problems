package bs.dac;

/**
 * 4
 *
 * ======
 *
 * Task.
 *
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * You may assume nums1 and nums2 cannot be both empty.
 *
 * ======
 *
 * Source: Leetcode
 */
public class MedianOfTwoSortedArrays {
	/**
	 * We make decisions in regards to a smaller array, ar1.
	 * We try to find mid in ar1 (mid1) and ar2 (mid2), if we have 'perfect condition', we can cal
	 * the answer, otherwise we move mid1 left or right, like in bs.
	 *
	 * Complexity is O(log(max(n,m))) - logarithmic.
	 *
	 * https://www.educative.io/edpresso/how-to-find-the-median-of-two-sorted-arrays-in-cpp
	 */
	public static class Solution {
		public double findMedianSortedArrays(int[] ar1, int[] ar2) {

			//         left part | right part
			// ar1 : [0 .. mid-1 | mid .. n]
			// ar2 : [0 .. mid-1 | mid .. n]
			// ultimately (perfect condition)
			// if we have ar1[mid-1] <= ar2[mid]
			// and ar2[mid-1] <= ar1[mid]
			// we can say that left part elements are smaller then right part elements
			// and to find median we can just use the formula
			// median = (max(ar1[mid-1],ar2[mid-1]) + min(ar1[mid],ar2[mid])) / 2
			// to simplify lets call mid-1 elements maxLeft - max on the left side
			// and mid - minRight - min on the right side

			int n1 = ar1.length;
			int n2 = ar2.length;

			// we want to move through smaller array
			if (n2 < n1) {
				return findMedianSortedArrays(ar2, ar1);
			}

			int lo = 0;
			int hi = n1;
			int n = n1 + n2;
			int k = (n + 1) / 2;

			while (hi - lo >= 0) {

				// pick mid in ar1
				int mid1 = (lo + hi) / 2;

				// since we want to have almost the same number of elements on the left
				// and on the right we substract mid1
				int mid2 = k - mid1;

				// maxLeftAr1 = ar1[mid1-1], simple, but if we mid1 is 0
				// we use Integer.MIN_VALUE, similar with minRight
				int maxLeftPartAr1 = mid1 == 0 ? Integer.MIN_VALUE : ar1[mid1 - 1];
				int minRightPartAr1 = mid1 == n1 ? Integer.MAX_VALUE : ar1[mid1];

				int maxLeftPartAr2 = mid2 == 0 ? Integer.MIN_VALUE : ar2[mid2 - 1];
				int minRightPartAr2 = mid2 == n2 ? Integer.MAX_VALUE : ar2[mid2];

				// perfect condition - we can calculate median
				if ((maxLeftPartAr1 <= minRightPartAr2) && (maxLeftPartAr2 <= minRightPartAr1)) {

					// if size is even we need (a + b) / 2
					if (n % 2 == 0) {
						int mostRightOnTheLeft = Math.max(maxLeftPartAr1, maxLeftPartAr2);
						int mostLeftOnTheRight = Math.min(minRightPartAr1, minRightPartAr2);
						return (mostRightOnTheLeft + mostLeftOnTheRight) / 2.0;
					}

					// size is odd - we need 1 middle element only
					else {
						return Math.max(maxLeftPartAr1, maxLeftPartAr2);
					}
				}

				// ar1 partition is too big, move left
				else if (maxLeftPartAr1 > minRightPartAr2) {
					hi = mid1 - 1;
				}

				// ar1 partition is too small, move right
				else {
					lo = mid1 + 1;
				}
			}

			return 0.0;
		}
	}
}