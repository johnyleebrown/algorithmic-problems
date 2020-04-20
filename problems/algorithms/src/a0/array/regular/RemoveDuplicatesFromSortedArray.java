package a0.array.regular;

/**
 * 26
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 *
 * For example, Given input array nums = [1,1,2],
 * Your function should return length = 2, with the first two elements
 * of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.
 */
public class RemoveDuplicatesFromSortedArray {

    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = 0 ; j < nums.length ; i++, j++){
            nums[i] = nums[j];
            while (j < nums.length - 1 && nums[j] == nums[j + 1]) j++;
        }
        return i;
    }
}
