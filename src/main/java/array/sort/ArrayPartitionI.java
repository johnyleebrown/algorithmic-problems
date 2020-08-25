package array.sort;

import java.util.Arrays;

/**
 * 561
 * Given an array of 2n integers, your task is to group these integers into n pairs of integer,
 * say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.
 * Input: [1,4,3,2] ; Output: 4
 * n is a positive integer, which is in the range of [1, 10000].
 * All the integers in the array will be in the range of [-10000, 10000].
 */
public class ArrayPartitionI {

    // O(nlogn + n), O(1)
    class Solution {
        public int arrayPairSum(int[] nums) {
            Arrays.sort(nums);
            int sum = 0;
            for (int i = 0 ; i < nums.length ; i+=2) sum+=nums[i];
            return sum;
        }
    }

    class Solution2{
        public int arrayPairSum(int[] nums) {
            int [] map = new int [20001];
            for (int i : nums) map[i + 10000]++;
            int d = 0;
            int n = 0;
            for (int i = 0; i < 20001; ++i) {
                if(map[i] != 0){
                    n += (map[i] + 1 - d) / 2 * (i - 10000);
                    d = (map[i] - d + 2) % 2;
                }
            }
            return n;
        }
    }
}
