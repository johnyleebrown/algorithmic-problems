package array.other;

import java.util.Arrays;

/**
 * 506
 * Given scores of N athletes, find their relative ranks and the people with the top three highest scores,
 * who will be awarded medals: "Gold Medal", "Silver Medal" and "Bronze Medal".
 * N is a positive integer and won't exceed 10,000.
 * All the scores of athletes are guaranteed to be unique.
 */
public class RelativeRanks {
    public class Solution {
        public String[] findRelativeRanks(int[] nums) {
            Integer[] index = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) index[i] = i;
            Arrays.sort(index, (a, b) -> (nums[b] - nums[a]));
            String[] result = new String[nums.length];

            for (int i = 0; i < nums.length; i++) {
                if (i == 0) result[index[i]] = "Gold Medal";
                else if (i == 1) result[index[i]] = "Silver Medal";
                else if (i == 2) result[index[i]] = "Bronze Medal";
                else result[index[i]] = (i + 1) + "";
            }
            return result;
        }
    }
}
