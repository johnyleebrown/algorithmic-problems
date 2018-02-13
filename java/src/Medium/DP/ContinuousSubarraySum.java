package Medium.DP;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 523
 * Given a list of non-negative numbers and a target integer k,
 * write a function to check if the array has a continuous
 * subarray of size at least 2 that sums up to the multiple of k,
 * that is, sums up to n*k where n is also an integer.
 */
public class ContinuousSubarraySum {
    /**
     * Time complexity: O(n^2)
     * Space complexity: O(n)
     */
    public static boolean solution(int[] nums, int k) {
        for (int i = 0; i < nums.length - 1; i++) if (nums[i] == 0 && nums[i + 1] == 0) return true;
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) sum += nums[i];
        if (nums.length < 2 || (k == 0 && sum != 0)) return false;
        if (sum == 0 && k == 0) return true;

        k = Math.abs(k);
        int amount = k >= sum ? k : (sum / k) * k;
        if (amount == sum || sum == k) return true;
        int[] div = new int[Math.abs(amount / k)];
        for (int i = 0; i < div.length; i ++) div[i] = k * (i + 1);
        for (int i = 0; i < div.length; i++) {
            Queue<Integer> q1 = new LinkedList<>();
            int max = q1.size();
            for (int j = 0; j < nums.length; j++) {
                max = Math.max(max, q1.size());
                Queue<Integer> q2 = new LinkedList<>();
                while (!q1.isEmpty()) {
                    int item = q1.poll();
                    if (nums[j] == item) return true;
                    if (nums[j] < item) q2.add(item - nums[j]);
                }
                if (nums[j] < div[i]) q2.add(div[i] - nums[j]);
                q1 = q2;
            }
        }
        return false;
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(k)
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(){{put(0,-1);}};;
        int runningSum = 0;
        for (int i=0;i<nums.length;i++) {
            runningSum += nums[i];
            if (k != 0) runningSum %= k;
            Integer prev = map.get(runningSum);
            if (prev != null) {
                if (i - prev > 1) return true;
            }
            else map.put(runningSum, i);
        }
        return false;
    }
}
