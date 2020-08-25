package regular.array;

/**
 * 88
 */
public class MergeSortedArray {
	public static class Solution {
		public void merge(int[] nums1, int m, int[] nums2, int n) {
			int end = nums1.length - 1;
			while (n != 0 && m != 0) {
				nums1[end--] = nums1[m - 1] >= nums2[n - 1] ? nums1[--m] : nums2[--n];
			}
			if (m == 0) {
				while (n != 0) {
					nums1[--n] = nums2[n];
				}
			}
		}
	}
}
