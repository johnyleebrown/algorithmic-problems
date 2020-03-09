package math.bits;

import java.util.HashSet;
import java.util.Set;

/**
 * 421
 * Given a non-empty array of numbers, Find the maximum result of XOR ai, aj in O(n)
 */
public class MaximumXOROfTwoNumbersInAnArray
{
    public class Solution
    {
        public int findMaximumXOR(int[] nums)
        {
            int max = 0, mask = 0;
            for (int i = 31; i >= 0; i--)
            {
                mask = mask | (1 << i);
                Set<Integer> set = new HashSet<>();
                for (int num : nums)
                {
                    set.add(num & mask);
                }
                int tmp = max | (1 << i);
                for (int prefix : set)
                {
                    if (set.contains(tmp ^ prefix))
                    {
                        max = tmp;
                        break;
                    }
                }
            }
            return max;
        }
    }
}
