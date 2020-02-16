package sort.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 347
 */
public class TopKFrequentElements
{
    /**
     * Bucket sort. Optimized with min and max counts.
     */
    class Solution
    {
        public List<Integer> topKFrequent(int[] nums, int k)
        {
            // get counts
            Map<Integer, Integer> m = new HashMap<>();
            int min = nums.length, max = 0;
            for (int i: nums)
            {
                int x = m.getOrDefault(i, 0) + 1;
                m.put(i, x);
                if (min > x)
                {
                    min = x;
                }
                if (max < x)
                {
                    max = x;
                }
            }
            // fill buckets
            List<Integer>[] a = new List[max - min + 1];
            for (int i: m.keySet())
            {
                int c = m.get(i);
                int pos = c - min;
                if (a[pos] == null)
                {
                    a[pos] = new ArrayList<>();
                }
                a[pos].add(i);
            }
            // get k top elements
            List<Integer> ans = new ArrayList<>();
            int c = 0;
            for (int i = a.length - 1; c < k && i >= 0; i--)
            {
                if (a[i] == null)
                {
                    continue;
                }
                for (int x: a[i])
                {
                    ans.add(x);
                    c++;
                    if (c == k)
                    {
                        return ans;
                    }
                }
            }
            throw new RuntimeException("k should be < n");
        }
    }
}