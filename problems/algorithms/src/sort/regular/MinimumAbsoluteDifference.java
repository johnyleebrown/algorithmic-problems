package sort.regular;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1200
 */
public class MinimumAbsoluteDifference
{
    class Solution
    {
        public List<List<Integer>> minimumAbsDifference(int[] arr)
        {
            // sort (O(nlogn))
            Arrays.sort(arr);

            // find the min diff (O(n))
            int minDiff = findMinDiff(arr);

            // find pairs with min diff (O(n))
            return createMinDiffPairs(arr, minDiff);
        }

        private List<List<Integer>> createMinDiffPairs(int[] arr, int minDiff)
        {
            List<List<Integer>> minDiffPairs = new ArrayList<>();
            for (int i = 0; i < arr.length - 1; i++)
            {
                if (arr[i + 1] - arr[i] == minDiff)
                {
                    minDiffPairs.add(createAPair(arr[i], arr[i + 1]));
                }
            }

            return minDiffPairs;
        }

        private List<Integer> createAPair(int a, int b)
        {
            List<Integer> newPair = new ArrayList<>();
            newPair.add(a);
            newPair.add(b);

            return newPair;
        }

        private int findMinDiff(int[] arr)
        {
            int minDiff = Integer.MAX_VALUE;

            for (int i = 0; i < arr.length - 1; i++)
            {
                minDiff = Math.min(minDiff, arr[i + 1] - arr[i]);
            }

            return minDiff;
        }
    }
}