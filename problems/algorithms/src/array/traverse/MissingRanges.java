package array.traverse;

import java.util.ArrayList;
import java.util.List;

/**
 * 163
 */
public class MissingRanges {
    class Solution {
        private String sep = "->";
        private List<String> ans = new ArrayList<>();

        public List<String> findMissingRanges(int[] nums, int lower, int upper) {
            int n = nums.length;

            // just for cases of substraction of big numbers
            long lo = lower, up = upper;

            // special case
            if (n == 0) {
                checkEmptyCase(lo, up);
                return ans;
            }

            // other special case
            if (n == 2 && nums[0] == lower && nums[1] == upper) {
                checkRegularCase(lo, up);
                return ans;
            }

            // start case
            if (nums[0] != lower) {
                checkEdgeCase(lo, nums[0], lo, nums[0] - 1, lo);
            }

            // regular case
            for (int i = 0; i < n - 1; i++) {
                checkRegularCase(nums[i], nums[i + 1]);
            }

            // end case
            if (nums[n - 1] != upper) {
                checkEdgeCase(nums[n - 1], up, nums[n - 1] + 1, up, up);
            }

            return ans;
        }

        private void checkRegularCase(long l, long r) {
            if (r - l == 2) {
                ans.add(String.valueOf(r - 1));
            } else if (r - l > 2) {
                ans.add(String.valueOf((l + 1) + sep + (r - 1)));
            }
        }

        private void checkEmptyCase(long l, long r) {
            if (l == r) {
                ans.add(String.valueOf(l));
            } else {
                ans.add(String.valueOf(l + sep + r));
            }
        }

        private void checkEdgeCase(long l, long r, long lToAdd, long rToAdd, long extra) {
            if (r - l == 1) {
                ans.add(String.valueOf(extra));
            } else {
                ans.add(String.valueOf(lToAdd + sep + rToAdd));
            }
        }

    }


}
