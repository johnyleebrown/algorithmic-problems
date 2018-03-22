package Medium.Array;

/**
 * 287
 *
 * Given an array nums containing n + 1 integers where each integer is
 * between 1 and n (inclusive), prove that at least one duplicate number
 * must exist. Assume that there is only one duplicate number, find the
 * duplicate one.
 *
 * Note:
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 */
public class FindTheDuplicateNumber {
    /**
     * Time complexity: O(n^2)
     * Space complexity: O(1)
     */
    public static int solution1(int[] nums) {
        int dub = -1;

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) return nums[i];
            }
        }

        return dub;
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public static int solution2(int[] nums) {
        int slow = nums[nums.length - 1];
        int fast = slow;

        while (true) {
            slow = nums[slow - 1];
            fast = nums[nums[fast - 1] - 1];
            if (slow == fast) break;
        }

        fast = nums[nums.length - 1];
        while (fast != slow) {
            slow = nums[slow - 1];
            fast = nums[fast - 1];
        }

        return fast;
    }
}
