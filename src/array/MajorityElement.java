package array;

/**
 * 169
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * You may assume that the array is non-empty and the majority element always exist in the array.
 */
public class MajorityElement {

    // O(n), O(1)
    class Solution {
        public int majorityElement(int nums[]) {
            int ind = 0, c = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[ind] == nums[i]) c++;
                else c--;
                if (c == 0) {
                    ind = i;
                    c = 1;
                }
            }
            return nums[ind];
        }
    }
}
