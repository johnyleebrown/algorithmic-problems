package array;

/**
 * 88
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * ou may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 */
public class MergeSortedArray {

    // O(n), n - nums1 length ; O(1)
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int end = nums1.length - 1;
        while (n != 0 && m != 0){
            nums1[end--] = nums1[m - 1] >= nums2[n - 1] ? nums1[--m] : nums2[--n];
        }
        if (m == 0) {
            while (n != 0){
                nums1[--n] = nums2[n];
            }
        }
    }
}
