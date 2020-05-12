package array.sum;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * KSum
 */
public class KSum {
    List<List<Integer>> ans = new LinkedList<>();

    /**
     * Calculates unique subsets of size k where sum equals target.
     */
    public List<List<Integer>> getUniqueSubsets(int k, int[] nums, int target) {
        Arrays.sort(nums);
        kSum(0, k, target, new LinkedList<>(), nums);
        return ans;
    }

    private void kSum(int start, int n, int leftSum, List<Integer> curSubset, int[] nums) {
        if (n == 2) {
            int left = start;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] == leftSum) {
                    // add ans
                    addList(curSubset, nums[left], nums[right]);

                    // skip duplicates at left side
                    while (left + 1 < right && nums[left] == nums[left + 1])
                        left++;
                    // skip duplicates at right side
                    while (right - 1 > left && nums[right] == nums[right - 1])
                        right--;

                    // go to next nums
                    left++;
                    right--;
                } else if (nums[left] + nums[right] < leftSum) {
                    while (left + 1 < right && nums[left] == nums[left + 1])
                        left++;
                    left++;
                } else {
                    while (right - 1 > left && nums[right] == nums[right - 1])
                        right--;
                    right--;
                }
            }
        } else {
            for (int i = start; i < nums.length - n + 1; i++) {
                // skip duplicates on next iteration
                if (i > start && nums[i] == nums[i - 1]) continue;
                curSubset.add(nums[i]);
                kSum(i + 1, n - 1, leftSum - nums[i], curSubset, nums);
                curSubset.remove(curSubset.size() - 1);
            }
        }
    }

    private void addList(List<Integer> local, int... nums) {
        ans.add(new LinkedList<>(local));
        for (int i : nums) ans.get(ans.size() - 1).add(i);
    }
}
