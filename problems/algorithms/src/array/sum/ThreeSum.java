package array.sum;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 15
 */
public class ThreeSum {
    /**
     * Sort, ONE - pick one by one, TWO and THREE - find in [l,r].
     */
    public static class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> ans = new LinkedList<>();
            Arrays.sort(nums);
            int n = nums.length;
            for (int i = 0; i < n - 2; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) continue;
                int cur = -nums[i];
                int left = i + 1;
                int right = n - 1;
                while (left < right) {
                    /*
                    cur = 7
                    1 2 3 4 5 6
                    |         |
                    1 2 3 4 5 6
                    ->|     |<-
                    */
                    if (nums[left] + nums[right] == cur) {
                        addList(ans, nums[i], nums[left], nums[right]);
                        while (left + 1 < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (right - 1 > left && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    }
                    /*
                    cur = 8, sum 1+6 - too small => pick a bigger value
                    1 2 3 4 5 6
                    |         |
                    1 2 3 4 5 6
                    ->|       |
                    */
                    else if (nums[left] + nums[right] < cur) {
                        while (left + 1 < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                    }
                    /*
                    cur = 6, sum 1+6 - too big => pick a smaller value
                    1 2 3 4 5 6
                    |         |
                    1 2 3 4 5 6
                    |       |<-
                    */
                    else {
                        while (right - 1 > left && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    }
                }
            }
            return ans;
        }

        private void addList(List<List<Integer>> ans, int... nums) {
            List<Integer> local = new LinkedList<>();
            for (int i : nums) local.add(i);
            ans.add(local);
        }
    }

    /**
     * Template solution. k = 3.
     */
    public static class Solution2 {
        List<List<Integer>> ans = new LinkedList<>();
        int k = 3;

        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            f(0, 3, nums, new LinkedList<>(), 0);
            return ans;
        }

        private void f(int start, int n, int[] nums, List<Integer> others, int cur) {
            if (n == 2) {
                int left = start;
                int right = nums.length - 1;
                while (left < right) {
                    if (nums[left] + nums[right] == cur) {
                        addList(others, nums[left], nums[right]);
                        while (left + 1 < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (right - 1 > left && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if (nums[left] + nums[right] < cur) {
                        while (left + 1 < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                    } else {
                        while (right - 1 > left && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    }
                }
            } else {
                for (int i = start; i < nums.length - n + 1; i++) {
                    if (i > 0 && nums[i] == nums[i - 1]) continue;
                    others.add(nums[i]);
                    f(i + 1, n - 1, nums, others, cur - nums[i]);
                    others.remove(others.size() - 1);
                }
            }
        }

        private void addList(List<Integer> local, int... nums) {
            for (int i : nums) local.add(i);
            ans.add(new LinkedList<>(local));
            for (int i : nums) local.remove(local.size() - 1);
        }
    }
}