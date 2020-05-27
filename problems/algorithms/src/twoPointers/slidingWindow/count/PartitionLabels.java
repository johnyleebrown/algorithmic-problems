package twoPointers.slidingWindow.count;

import java.util.LinkedList;
import java.util.List;

/**
 * 763
 *
 * ======
 *
 * Task.
 *
 * A string S of lowercase letters is given. We want to partition this string
 * into as many parts as possible so that each letter appears in at most one
 * part, and return a list of integers representing the size of these parts.
 *
 * ======
 *
 * Source: Leetcode
 */
public class PartitionLabels {
    /**
     * Precount like in 76. Then like in 'count max number of intersecting
     * intervals' wait until count is zero - this means that we have no
     * intervals at the moment.
     */
    public static class Solution {
        public List<Integer> partitionLabels(String s) {
            List<Integer> ans = new LinkedList<>();
            if (s.length() == 0) return ans;

            // get all counts
            char[] chars = s.toCharArray();
            int[] cc = new int[26];
            for (int i = 0; i < chars.length; i++) {
                cc[chars[i] - 'a']++;
            }

            int count = 0;
            boolean[] seen = new boolean[26];
            int l = 0;
            for (int r = 0; r < chars.length; r++) {
                int x = chars[r] - 'a';
                cc[x]--;

                // start interval
                if (!seen[x]) {
                    seen[x] = true;
                    count++;
                }

                // end interval
                if (cc[x] == 0) {
                    count--;
                }

                // found all intersecting intervals
                if (count == 0) {
                    ans.add(r - l + 1);
                    l = r + 1;
                }
            }

            return ans;
        }
    }
}