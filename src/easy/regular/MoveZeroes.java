package Easy.Array;

/**
 * 283
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 */
public class MoveZeroes {
    // O(n), O(1)
    public class Solution {
        public void moveZeroes(int[] nums) {
            int i = 0, j = 0;
            while (j < nums.length) {
                if (nums[j] != 0) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    i++;
                }
                j++;
            }
        }
    }
}
