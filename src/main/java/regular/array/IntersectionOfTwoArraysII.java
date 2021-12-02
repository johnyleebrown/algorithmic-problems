package regular.array;

/**
 * 350
 */
public class IntersectionOfTwoArraysII {

    public static class Solution {

        public int[] intersect(int[] nums1, int[] nums2) {
            int[] a = new int[1001], b = new int[1001];
            for (int val : nums1) {
                a[val]++;
            }
            int count = 0;
            for (int val : nums2) {
                if (a[val] > 0) {
                    b[val]++;
                    a[val]--;
                    count++;
                }
            }
            int[] ans = new int[count];
            for (int i = 0, j = 0; i < 1001; i++) {
                while (--b[i] >= 0) {
                    ans[j++] = i;
                }
            }
            return ans;
        }
    }

    /**
     * What if the given array is already sorted? How would you optimize your algorithm?
     *
     * 2 pointers
     *
     * What if nums1's size is small compared to nums2's size? Which algorithm is better?
     *
     * Use map for nums1 and not for nums2
     *
     * What if elements of nums2 are stored on disk, and the memory is limited such that you
     * cannot load all elements into the memory at once?
     *
     * Go through ranges of numbers that can fit in memory. Since we know that max is 10^5 -
     * go through [0..9999] .. ranges by 10k numbers lets say.
     */
    public static class Solution2 {
    }
}
