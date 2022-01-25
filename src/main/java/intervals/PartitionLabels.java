package intervals;

import java.util.ArrayList;
import java.util.Arrays;
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
     * Find intervals, then while merging record them.
     */
    public static class Solution {
        public List<Integer> partitionLabels(String s) {
            List<Integer> ans = new LinkedList<>();
            if (s.length() == 0) return ans;

            // find intervals
            char[] chars = s.toCharArray();
            List<int[]> l = new ArrayList<>();
            int[] startOfLetters = new int[26];
            Arrays.fill(startOfLetters, -1);

            for (int i = 0; i < chars.length; i++) {
                int x = chars[i] - 'a';
                if (startOfLetters[x] == -1) {
                    l.add(new int[]{i, i});
                    startOfLetters[x] = l.size() - 1;
                } else {
                    l.get(startOfLetters[x])[1] = i;
                }
            }

            // merge intervals
            int start, end;
            int i = 0;
            while (i < l.size()) {
                start = l.get(i)[0];
                end = l.get(i)[1];
                while (i < l.size() && l.get(i)[0] <= end) {
                    end = Math.max(end, l.get(i)[1]);
                    i++;
                }
                ans.add(end - start + 1);
                if (i == l.size()) break;
            }

            return ans;
        }
    }
}