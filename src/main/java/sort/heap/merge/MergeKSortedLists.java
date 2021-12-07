package sort.heap.merge;

import commons.ListNode;

import java.util.PriorityQueue;

/**
 * 23
 */
public class MergeKSortedLists {

  /**
   * Check 378 first - first we add all smallest elements - first column, then add
   * neighbors 1 by 1.
   */
  public static class Solution {

    public ListNode mergeKLists(ListNode[] lists) {
      PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> (a.val - b.val));
      for (ListNode head : lists) {
        if (head != null) pq.add(head);
      }
      ListNode head = null, start = null;
      while (!pq.isEmpty()) {
        ListNode cur = pq.poll();
        if (head == null) {
          head = cur;
          start = cur;
        } else {
          head.next = cur;
          head = head.next;
        }
        if (cur.next != null) pq.add(cur.next);
      }
      return start;
    }
  }
}