package sort.heap.merge;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 373
 *
 * ======
 *
 * Task.
 *
 * You are given two integer arrays nums1 and nums2 sorted in ascending order
 * and an integer k.
 *
 * Define a pair (u,v) which consists of one element from the first array and
 * one element from the second array.
 *
 * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 *
 * ======
 *
 * Source: Leetcode
 */
public class FindKPairsWithSmallestSums {
    /**
     * First, check 378.
     *
     * The main idea is that the best partner for each i from the nums1 is
     * nums2[0]. We start here and then we add right neighbor each time because
     * it is the next good variant after the current pair.
     *
     * 1 7 11
     * 2 4 6
     */
    public static class Solution {
        public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
            List<List<Integer>> ans = new LinkedList<>();
            int n1 = nums1.length;
            int n2 = nums2.length;
            if (n1 == 0 || n2 == 0) return ans;

            PriorityQueue<Element> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
            for (int i = 0; i < n1; i++) {
                pq.add(new Element(nums1[i] + nums2[0], i, 0));
            }

            while (--k >= 0 && !pq.isEmpty()) {
                Element x = pq.poll();
                addToList(ans, nums1[x.i1], nums2[x.i2]);

                if (x.i2 + 1 < n2) {
                    pq.add(new Element(nums1[x.i1] + nums2[x.i2 + 1], x.i1, x.i2 + 1));
                }
            }

            return ans;
        }

        private void addToList(List<List<Integer>> ans, int a, int b) {
            List<Integer> l = new LinkedList<>();
            l.add(a);
            l.add(b);
            ans.add(l);
        }

        private static class Element {
            int val, i1, i2;

            Element(int val, int i1, int i2) {
                this.val = val;
                this.i1 = i1;
                this.i2 = i2;
            }
        }
    }
}