package sort.heap;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * GetKMaxElements
 *
 * ======
 *
 * Task.
 *
 * You got heap as binary array. Get max k elements without modifying heap.
 *
 * ======
 *
 * Company: Google
 * Source: Leetcode
 */
public class GetKMaxElements {
    /**
     * Use pq + bfs for that.
     */
    public static class Solution {
        public List<Integer> solve(int[] heap, int k) {
            List<Integer> ans = new LinkedList<>();
            int n = heap.length;
            PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> b[1] - a[1]);

            q.add(new int[]{1, heap[1]});
            while (!q.isEmpty()) {
                int[] el = q.poll();
                ans.add(el[1]);
                if (ans.size() == k) return ans;
                int ind = el[0];
                if (2 * ind < n) {
                    q.add(new int[]{2 * ind, heap[2 * ind]});
                }
                if (2 * ind + 1 < n) {
                    q.add(new int[]{2 * ind + 1, heap[2 * ind + 1]});
                }
            }
            return ans;
        }
    }
}