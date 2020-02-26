package array.regular;

import java.util.Arrays;

/**
 * 581
 * Given an integer array, you need to find one continuous subarray
 * that if you only sort this subarray in ascending order, then the
 * whole array will be sorted in ascending order, too.
 * You need to find the shortest such subarray and output its length.
 */
public class ShortestUnsortedContinuousSubarray {

    // O(n(logn+1)), O(n)
    public int findUnsortedSubarray(int[] nums) {
        if (nums.length == 0 || nums.length == 1) return 0;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int start = -1, end = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length ; i++){
            if (sorted[i] != nums[i]){
                if (start == -1) start = i;
                end = Math.max(end, i);
            }
        }
        return start == -1 ? 0 : end - start + 1;
    }


    // O(n), O(1)
    public int findUnsortedSubarray2(int[] nums) {
        int start = 0, end = -1, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= max) max = nums[i];
            else end = i;
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] <= min) min = nums[i];
            else start = i;
        }
        return end - start + 1;
    }
}
