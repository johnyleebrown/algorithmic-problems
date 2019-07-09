package medium.binarySearch;

/**
 * 33
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2). You are given a target value to search. If
 * found in the array return its index, otherwise return -1. You may assume no duplicate exists in the array.
 */
public class SearchInRotatedSortedArray {
    /**
     * Time complexity: O(log N)
     * Space complexity: O(1)
     */
    public static int solution2(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (nums[mid] == target)
                return mid;

            if (nums[lo] <= nums[mid])
                if (target >= nums[lo] && target < nums[mid])
                    hi = mid - 1;
                else
                    lo = mid + 1;
            else
                if (target > nums[mid] && target <= nums[hi])
                    lo = mid + 1;
                else
                    hi = mid - 1;

        }

        return -1;
    }

    /**
     * Idea: find normal part of array and do binary search
     * Time complexity: O(log N)
     * Space complexity: O(1)
     */
    public static int solution1(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (true) {
            if (hi < lo) return -1;
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] >= nums[lo]) { // normal
                if (target >= nums[lo] && target <= nums[mid]) return bs(lo, mid, target, nums);
                else lo = mid + 1;
            } else if (nums[mid] <= nums[hi]) { // normal
                if (target >= nums[mid] && target <= nums[hi]) return bs(mid, hi, target, nums);
                else hi = mid - 1;
            }
        }
    }

    private static int bs(int lo, int hi, int val, int[] ar) {
        if (hi < lo) return -1;
        int mid = lo + (hi - lo) / 2;
        if (val < ar[mid]) return bs(lo, mid - 1, val, ar);
        else if (val > ar[mid]) return bs(mid + 1, hi, val, ar);
        else return mid;
    }

    /**
     * Idea
     * Array [12, 13, 14, 15, 16, 17, 18, 19, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
     * If target is let’s say 14 [12, 13, 14, 15, 16, 17, 18, 19, inf, inf, inf, inf, inf, inf, inf, inf, inf, inf, inf, inf]
     * If target is let’s say 7 [-inf, -inf, -inf, -inf, -inf, -inf, -inf, -inf, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
     * by StefanPochmann
     */
    public static int solution3(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            // If nums[mid] and target are “on the same side” of nums[0], we just take nums[mid]
            // Otherwise we use -infinity or +infinity as needed.
            int num = (nums[mid] < nums[0]) == (target < nums[0])
                    ? nums[mid]
                    : target < nums[0] ? Integer.MIN_VALUE : Integer.MAX_VALUE;

            if (num < target)       lo = mid + 1;
            else if (num > target)  hi = mid - 1;
            else                    return mid;
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(solution2(new int[]{4, 5, 6, 7, 8, 0, 1, 2}, 1));
        System.out.println(solution2(new int[]{1, 3, 5}, 3));
        System.out.println(solution2(new int[]{3, 1}, 3));
    }

}
