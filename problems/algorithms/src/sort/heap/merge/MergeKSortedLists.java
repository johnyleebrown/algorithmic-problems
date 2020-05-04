package sort.heap.merge;

import util.ds.ListNode;

import java.util.PriorityQueue;

/**
 * 23
 *
 * ======
 *
 * Task.
 *
 * Merge k sorted linked lists and return it as one sorted list. Analyze and
 * describe its complexity.
 *
 * ======
 *
 * Source: Leetcode
 */
public class MergeKSortedLists {
    /**
     * Check 378 first - first we add all smallest elements - first column, then
     * add neighbors 1 by 1.
     */
    public static class Solution {
        public ListNode mergeKLists(ListNode[] l) {
            PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
            for (ListNode node : l) {
                if (node != null) pq.add(node);
            }
            ListNode root = null;
            ListNode cur = null;
            while (!pq.isEmpty()) {
                ListNode x = pq.poll();
                if (root == null) {
                    root = x;
                    cur = root;
                } else {
                    cur.next = x;
                    cur = cur.next;
                }
                if (x.next != null) pq.add(x.next);
            }
            return root;
        }
    }
}