package twoPointers.regular;

/**
 * 457
 *
 * ======
 *
 * Task.
 *
 * You are given a circular array nums of positive and negative integers. If a number k at an index
 * is positive, then move forward k steps. Conversely, if it's negative (-k), move backward k steps.
 * Since the array is circular, you may assume that the last element's next element is the first
 * element, and the first element's previous element is the last element.
 *
 * Determine if there is a loop (or a cycle) in nums. A cycle must start and end at the same index
 * and the cycle's length > 1. Furthermore, movements in a cycle must all follow a single direction.
 * In other words, a cycle must not consist of both forward and backward movements.
 *
 * ======
 *
 * Source: Leetcode
 */
public class CircularArrayLoop {
	/**
	 * We start a pointer from every index in array and check if we have a cycle - meaning if we
	 * could reach the the index we started from.
	 */
	public static class Solution {
		public boolean circularArrayLoop(int[] ar) {

			int n = ar.length;

			for (int i = 0; i < n; i++) {

				boolean positiveSign = ar[i] > 0;
				// mark indexes that we ve seen on our path from i to j
				boolean[] seen = new boolean[n];
				int j = i;
				seen[i] = true;
				// how many steps we have taken from i to j
				int pathLen = 0;

				while ((ar[j] > 0) == positiveSign) {

					j = getNewIndex(ar, j);
					pathLen++;

					// if we stepped on something we have seen
					if (seen[j]) {
						// either we found a loop
						// if j is not i it means it is not a loop
						// at this loop we only check if we can reach the point where we started at
						if (j == i && pathLen > 1) {
							return true;
						}
						// no point in continuing because we don't have i-indexed loop
						// we will be just looping in j
						else {
							break;
						}
					}

					seen[j] = true;
				}
			}

			return false;
		}

		private int getNewIndex(int[] ar, int curInd) {
			int jump = ar[curInd];
			int arLen = ar.length;
			if (jump < 0) {
				// n=7 i=2 j=-13
				return (((curInd + jump) % arLen) + arLen) % arLen;
			} else {
				// n=7 i=2 j=13
				return (curInd + jump) % arLen;
			}
		}
	}
}