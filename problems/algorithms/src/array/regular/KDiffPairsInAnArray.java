package array.regular;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 532
 * Given an array of integers and an integer k,
 * you need to find the number of unique k-diff pairs in the array.
 * Here a k-diff pair is defined as an integer pair (i, j),
 * where i and j are both numbers in the array and their absolute difference is k.
 */

public class KDiffPairsInAnArray {


    // Time : O(n) + O(n + s)
    public int findPairs(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i : nums) map.put(i, map.getOrDefault(i, 0) + 1);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (k == 0) {
                if (entry.getValue() >= 2) count++;
            }
            else {
                if (map.containsKey(entry.getKey() + k)) count++;
            }
        }
        return count;
    }

    // Time : O(n) ; Space : O(1)
    public int findPairs2(int[] nums, int k) {
        if (k < 0 || nums.length <= 1) return 0;

        Arrays.sort(nums);
        int count = 0;
        int left = 0;
        int right = 1;

        while (right < nums.length){
            int firstNum = nums[left];
            int secondNum = nums[right];
            if (secondNum - firstNum < k) right++;
            else if (secondNum - firstNum > k) left++;
            else {
                count++;
                while (left < nums.length && nums[left] == firstNum) left++;
                while (right < nums.length && nums[right] == secondNum) right++;

            }
            if (right == left) right++;
        }
        return count;
    }
}
