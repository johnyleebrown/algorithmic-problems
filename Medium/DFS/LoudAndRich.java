package medium.dfs;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * 851
 *
 * Topics: DFS, GRAPH
 *
 * In a group of N people (labelled 0, 1, 2, ..., N-1), each person has different amounts of money, and different levels of quietness.
 * For convenience, we'll call the person with label x, simply "person x".
 * We'll say that richer[i] = [x, y] if person x definitely has more money than person y.  Note that richer may only be a subset of valid observations.
 * Also, we'll say quiet[x] = q if person x has quietness q.
 * Now, return answer, where answer[x] = y if y is the least quiet person (that is, the person y with the smallest value of quiet[y]), among all people who definitely have equal to or more money than person x.
 */
public class LoudAndRich {
    /**
     * TLE
     *
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    class Solution {
        public int[] loudAndRich(int[][] richer, int[] quiet) {
            HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();
            for (int[] r : richer) {
                map.putIfAbsent(r[1], new LinkedList<>());
                map.get(r[1]).add(r[0]);
            }

            int[] answer = new int[quiet.length];
            for (int i = 0 ; i < quiet.length; i++)
                answer[i] = helper(map, quiet, i, quiet[i])[0];

            return answer;
        }

        int[] helper(HashMap<Integer, LinkedList<Integer>> map,
                     int[] quiet, int i, int minQuite) {
            int[] m = new int[]{i, minQuite};

            for (int k : map.getOrDefault(i, new LinkedList<>())) {
                if (m[1] > quiet[k]) {
                    m[0] = k;
                    m[1] = quiet[k];
                }

                if (map.containsKey(k)) {
                    int[] x = helper(map, quiet, k, m[1]);
                    if (m[1] > x[1]) m = x;
                }
            }

            return m;
        }
    }

    /**
     * Using memoization
     *
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    class SolutionMEMO {
        public int[] loudAndRich(int[][] richer, int[] quiet) {
            HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();
            for (int[] r : richer) {
                map.putIfAbsent(r[1], new LinkedList<>());
                map.get(r[1]).add(r[0]);
            }

            int[] answer = new int[quiet.length];
            for (int i = 0 ; i < quiet.length; i++)
                answer[i] = helper(map, quiet, i, quiet[i], new boolean[quiet.length])[0];

            return answer;
        }

        int[] helper(HashMap<Integer, LinkedList<Integer>> map,
                     int[] quiet, int i, int min, boolean[] marked) {
            int[] minQuite = new int[]{i, min};

            for (int k : map.getOrDefault(i, new LinkedList<>())) {
                if (minQuite[1] > quiet[k]) {
                    minQuite[0] = k;
                    minQuite[1] = quiet[k];
                }

                if (map.containsKey(k) && !marked[k]) {
                    int[] x = helper(map, quiet, k, minQuite[1], marked);
                    if (minQuite[1] > x[1]) minQuite = x;
                }
            }

            return minQuite;
        }
    }
}
