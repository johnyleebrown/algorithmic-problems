package a0.array.regular;

import java.util.Arrays;

/**
 * 31
 */
public class NextPermutation {

    /*

    Find the largest index k such that nums[k] < nums[k + 1]. If no such index exists, just reverse nums and done.
    Find the largest index l > k such that nums[k] < nums[l].
    Swap nums[k] and nums[l].
    Reverse the sub-array nums[k + 1:].
     */

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
