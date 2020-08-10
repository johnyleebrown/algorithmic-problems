package array.ksum;

import java.util.*;

/**
 * 15
 */
public class ThreeSum {
    /**
     * Short version for the interview.
     */
    public static class SolutionForInterview {
        public List<List<Integer>> threeSum(int[] ar) {
            List<List<Integer>> ans = new LinkedList<>();
            int n = ar.length;
            if (n == 0) return ans;
            Set<List<Integer>> set = new HashSet<>();
            Arrays.sort(ar);
            // -4 -1 -1 0 1 2
            for (int i1 = 0; i1 < n - 2; i1++) {
                int sum = 0 - ar[i1];
                int i2 = i1 + 1;
                int i3 = n - 1;
                while (i2 < i3) {
                    if (ar[i2] + ar[i3] == sum) {
                        set.add(Arrays.asList(ar[i1], ar[i2], ar[i3]));
                        i2++;
                        i3--;
                    } else if (ar[i2] + ar[i3] < sum) {
                        i2++;
                    } else {
                        i3--;
                    }
                }
            }
            ans.addAll(set);
            return ans;
        }
    }

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