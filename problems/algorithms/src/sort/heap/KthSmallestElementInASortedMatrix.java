package sort.heap;

import java.util.PriorityQueue;

/**
 * 378
 *
 * ======
 *
 * Task.
 *
 * Given a n x n matrix where each of the rows and columns are sorted in
 * ascending order, find the kth smallest element in the matrix.
 *
 * Note that it is the kth smallest element in the sorted order, not the kth
 * distinct element.
 *
 * ======
 *
 * Source: Leetcode
 */
public class KthSmallestElementInASortedMatrix {
    /**
     * We know for sure that first row are the smallest elements there is. Then
     * for each element polled we add it's lower neighbor as it is the potential
     * next smallest element.
     */
    public static class Solution {
        public int kthSmallest(int[][] ar, int k) {
            int n = ar.length;
            if (k > n * n) return -1;
            PriorityQueue<Element> pq = new PriorityQueue<>((a, b) -> (a.val - b.val));
            for (int j = 0; j < n; j++) {
                pq.add(new Element(ar[0][j], 0, j));
            }
            int ans = -1;
            while (--k >= 0) {
                Element e = pq.poll();
                ans = e.val;
                if (e.i + 1 < n) {
                    pq.add(new Element(ar[e.i + 1][e.j], e.i + 1, e.j));
                }
            }
            return ans;
        }

        private class Element {
            int val, i, j;

            Element(int val, int i, int j) {
                this.val = val;
                this.i = i;
                this.j = j;
            }
        }
    }
}