package array;

/**
 * 485
 * Given a binary array, find the maximum number of consecutive 1s in this array.
 */
public class MaxConsecutiveOnes {
    // O(n)
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int index = 0;
        int result = 0;
        while (index < nums.length) {
            while (index < nums.length && nums[index] == 0) index++;
            int start = index;
            while (index < nums.length && nums[index] == 1) index++;
            result = Math.max(result, index - start);
        }
        return result;
    }
}
