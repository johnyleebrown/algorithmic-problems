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
     * Floyd's Tortoise and Hare (Cycle Detection)
     *
     * A cycle must exist. Because each number in nums is between 1 and n,
     * it will necessarily point to an index that exists.
     *
     * 0 1 2 3 4 5 6
     * 1 2 3 4 5 6 3
     *     ^       ^
     * find intersection (5)
     * now, like in {@link Medium.LinkedList.LinkedListCycleII}
     * go from intersection with 'slow' speed as well as from
     * nums[0] with the same speed to find the duplicate (3)
     *
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public static int solution2(int[] nums) {
        // Find the intersection point of the two runners.
        int slow = nums[0];
        int fast = nums[0];

        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) break;
        }

        // Find the "entrance" to the cycle.
        int pointer1 = nums[0];
        int pointer2 = slow;

        while (pointer1 != pointer2) {
            pointer1 = nums[pointer1];
            pointer2 = nums[pointer2];
        }

        return pointer1;
    }
}
