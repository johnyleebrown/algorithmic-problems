package Medium.Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 18
 *
 * Given an array S of n integers, are there elements a, b, c, and d in S
 * such that a + b + c + d = target? Find all unique quadruplets in the
 * array which gives the sum of target.
 *
 * For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
 *
 * A solution set is:
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 *
 * Note:
 *
 * There are Θ(n4) different quadruplets, about n^4 / 24
 * There are Θ(n) possible sums
 * At least one sum must have Ω(n3) different quadruplets.
 * For that sum, we must generate those Ω(n3) quadruplets.
 */
public class FourSum {
    /**
     * Time complexity: O(n^2)
     * Space complexity: O(n)
     */
    public static List<List<Integer>> solution(int[] nums, int target) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        Set<List<Integer>> set = new HashSet<>();

        for (int i = 0; i < nums.length - 3; i++) {

            if (i > 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] * 4 > target) break; // Too Big!!
            if (nums[i] + 3 * nums[nums.length - 1] < target) continue; //Too Small

            for (int j = i + 1; j < nums.length - 2; j++) {

                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                if (nums[j] * 3 > target - nums[i]) break; //Too Big
                if (nums[j] + 2 * nums[nums.length - 1] < target - nums[i]) continue; // Too Small

                int k = j + 1;
                int l = nums.length - 1;

                while (l > k) {
                    int sum = nums[i] + nums[j] + nums[k] + nums[l];

                    if (sum == target) {
                        List<Integer> a = Arrays.asList(nums[i], nums[j], nums[k], nums[l]);
                        if (set.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]))) res.add(a);
                        while (l > k && nums[k] == nums[k + 1]) k++;
                        k++;
                        while (l > k && nums[l] == nums[l - 1]) l--;
                        l--;
                    } else if (sum < target) {
                        while (l > k && nums[k] == nums[k + 1]) k++;
                        k++;
                    } else {
                        while (l > k && nums[l] == nums[l - 1]) l--;
                        l--;
                    }
                }
            }
        }

//        for (List l : res) System.out.println(Arrays.toString(l.toArray()));

        return res;
    }

    public static void main(String[] args) {
        solution(new int[]{-3, -2, -1, 0, 0, 1, 2, 3}, 0);
        /*
        new int[]{0,0,0,0}, 0
        new int[]{-1,0,1,2,-1,-4}, 1
        new int[]{1,0,-1,0,-2,2}, 0
         */
    }
}
