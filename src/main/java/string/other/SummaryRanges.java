package string.other;

import java.util.ArrayList;
import java.util.List;

/**
 * 228
 *
 * ======
 *
 * Task.
 *
 * Given a sorted integer array without duplicates, return the summary of its
 * ranges.
 *
 * ======
 *
 * Source: Leetcode
 */
public class SummaryRanges {
    public static class Solution {
        public List<String> summaryRanges(int[] ar) {
            int i = 0;
            int n = ar.length;
            List<String> ans = new ArrayList<>();
            if (n == 0) return ans;
            while (i < n) {
                int j = i;
                while (j + 1 < n && ar[j + 1] == ar[j] + 1) {
                    j++;
                }
                if (i == j) {
                    ans.add(String.valueOf(ar[i]));
                } else {
                    ans.add(ar[i] + "->" + ar[j]);
                }
                i = j + 1;
            }
            return ans;
        }
    }
}