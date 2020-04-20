package a0.array.regular;

import java.util.Arrays;

/**
 * 31
 *
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * The replacement must be in-place and use only constant extra memory.
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 */
public class NextPermutation {
    /**
     * Intuitive: find ceiling to the right iterating from the end
     * Here i don't take into account descending factor
     *
     * Time complexity: O(n + nlogn)
     * Space complexity: O(1)
     */
    class Solution1 {
        public void nextPermutation(int[] nums) {
            for (int i = nums.length - 2; i >= 0; i--) {
                int min = i + 1;
                boolean foundAtLeastOneMin = false;
                for (int j = nums.length - 1; j > i; j--) { // find min
                    if (nums[j] > nums[i] && nums[j] <= nums[min]) {
                        min = j;
                        foundAtLeastOneMin = true;
                    }
                }
                if (foundAtLeastOneMin) {
                    swap(nums, i, min);
                    Arrays.sort(nums, i + 1, nums.length);
                    return;
                }
            }
            Arrays.sort(nums);
        }

        void swap(int[] nums, int i, int j) {
            int x = nums[i];
            nums[i] = nums[j];
            nums[j] = x;
        }
    }

    /**
     * For any given sequence that is in descending order, no next larger permutation is possible
     *
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    class Solution2 {
        public void nextPermutation(int[] nums) {
            int i = nums.length - 2;
            while (i >= 0 && nums[i] >= nums[i + 1]) i--;
            if (i >= 0) {
                int j = nums.length - 1;
                while (nums[j] <= nums[i]) j--;
                swap(nums, i, j);
            }
            reverse(nums, i + 1, nums.length - 1);
        }

        void swap(int[] nums, int i, int j) {
            int x = nums[i];
            nums[i] = nums[j];
            nums[j] = x;
        }

        void reverse(int[] nums, int i, int j) {
            while (i < j) swap(nums, i++, j--);
        }
    }
}
