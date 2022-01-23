package sort.other;

import java.util.HashMap;
import java.util.Map;

/**
 * 1086
 *
 * ======
 *
 * Task.
 *
 * Given a list of scores of different students, return the average score of
 * each student's top five scores in the order of each student's id.
 *
 * Each entry items[i] has items[i][0] the student's id, and items[i][1] the
 * student's score.  The average score is calculated using integer division.
 *
 * ======
 *
 * Source: Leetcode
 */
public class HighFive {
    public static class Solution {
        public int[][] highFive(int[][] items) {
            Map<Integer, int[]> m = new HashMap<>();
            for (int[] i : items) {
                m.putIfAbsent(i[0], new int[101]);
                m.get(i[0])[i[1]]++;
            }
            int[][] ans = new int[m.size()][2];
            int ind = 0;
            for (int i : m.keySet()) {
                int k = 5;
                int sum = 0;
                for (int j = 100; j >= 1; j--) {
                    int x = m.get(i)[j];
                    if (x == 0) continue;
                    if (x > k) x = k;
                    k -= x;
                    while (--x >= 0) {
                        sum += j;
                    }
                    if (k == 0) break;
                }
                ans[ind][0] = i;
                ans[ind++][1] = sum / 5;
            }
            return ans;
        }
    }
}