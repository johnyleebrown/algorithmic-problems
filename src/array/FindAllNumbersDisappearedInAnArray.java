package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 448
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array),
 * some elements appear twice and others appear once.
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 * Could you do it without extra space and in O(n) runtime? You may assume the returned
 * list does not count as extra space.
 */
public class FindAllNumbersDisappearedInAnArray {

    class Solution {
        public List<Integer> findDisappearedNumbers(int[] nums) {
            List<Integer> ret = new ArrayList<Integer>();
            for (int i = 0; i < nums.length; i++) {
                int val = Math.abs(nums[i]) - 1;
                if (nums[val] > 0) nums[val] = -nums[val];
            }
            for (int i = 0; i < nums.length; i++)
                if (nums[i] > 0) ret.add(i + 1);
            return ret;
        }
    }

    class Solution2 {
        public List<Integer> findDisappearedNumbers(int[] nums) {
            List<Integer> res = new ArrayList<>();
            int n = nums.length;
            for (int i = 0; i < nums.length; i++)
                nums[(nums[i] - 1) % n] += n;
            for (int i = 0; i < nums.length; i++)
                if (nums[i] <= n) res.add(i + 1);
            return res;
        }
    }
}
