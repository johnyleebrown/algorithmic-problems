package array.counter;

/**
 * 169
 */
public class MajorityElement {
    class Solution {
        public int majorityElement(int[] nums) {
            int count = 0;
            Integer candidate = null;

            for (int num : nums) {
                if (count == 0) candidate = num;
                if (num == candidate) count++;
                else count--;
            }

            return candidate;
        }
    }
}
