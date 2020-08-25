package sort.heap.greedy;

import java.util.PriorityQueue;

/**
 * 1167
 *
 * ======
 *
 * Task.
 *
 * You have some sticks with positive integer lengths.
 *
 * You can connect any two sticks of lengths X and Y into one stick by paying a
 * cost of X + Y.  You perform this action until there is one stick remaining.
 *
 * Return the minimum cost of connecting all the given sticks into one stick in
 * this way.
 *
 * ======
 *
 * Source: Leetcode
 */
public class MinimumCostToConnectSticks {
    /**
     * SF.
     */
    public static class Solution {
        public int connectSticks(int[] ar) {
            PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> (a - b));
            for (int i : ar) q.add(i);
            int ans = 0;
            while (q.size() > 1) {
                int x = q.poll() + q.poll();
                ans += x;
                q.add(x);
            }
            return ans;
        }
    }
}