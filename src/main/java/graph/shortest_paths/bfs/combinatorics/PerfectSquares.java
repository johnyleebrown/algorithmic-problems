package graph.shortest_paths.bfs.combinatorics;

import java.util.*;

/**
 * 279
 */
public class PerfectSquares {
    /**
     * 1 1
     * 2 1+1
     * 3 1+1+1
     * 4 4
     * 5 4+1
     * 6 4+1+1
     * 7 4+1+1+1
     * 8 4+4
     * 9 9
     * 10 9+1
     * 11 9+1+1
     * 12 9+1+1+1 = 4+4+4
     */
    class Solution {
        public int numSquares(int n) {
            // a set for optimization
            Set<Integer> seen = new HashSet<>();

            // a queue for storing iteration values
            Queue<Integer> q = new LinkedList<>();

            // get all the possible powers less then n
            List<Integer> powers = new LinkedList<>();
            int highestSquareRoot = (int) Math.sqrt(n);
            for (int i = 1; i <= highestSquareRoot; i++) {
                int pow = i * i;
                powers.add(pow);
                q.add(pow);
            }

            int counter = 0;

            while (!q.isEmpty()) {
                counter++;
                int size = q.size();

                for (int i = 0; i < size; i++) {
                    int num = q.poll();
                    if (num == n) {
                        return counter;
                    }

                    for (int pow : powers) {
                        int sum = num + pow;

                        if (sum > n) {
                            continue;
                        }
                        if (!seen.add(sum)) {
                            continue;
                        }

                        q.add(sum);
                    }
                }
            }

            return counter;
        }
    }
}

