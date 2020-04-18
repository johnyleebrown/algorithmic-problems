package bs.ternarySearch;

/**
 * 162
 *
 * ======
 *
 * Task.
 *
 * A peak element is an element that is greater than its neighbors.
 *
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 *
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 *
 * You may imagine that nums[-1] = nums[n] = -∞.
 *
 * ======
 *
 * Source: Leetcode
 */
public class FindPeakElement {
    public static class Solution {
        public int findPeakElement(int[] a) {
            int lo = -1;
            int hi = a.length;
            while (hi - lo > 2) {
                int m1 = lo + (hi - lo) / 3;
                int m2 = lo + 2 * (hi - lo) / 3;
                if (a[m1] > a[m2]) {
                    hi = m2;
                } else {
                    lo = m1;
                }
            }
            return (hi + lo) / 2;
        }
    }
}