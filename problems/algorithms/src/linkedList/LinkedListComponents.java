package linkedList;

import util.ds.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 817
 *
 * ======
 *
 * Task.
 *
 * We are given head, the head node of a linked list containing unique integer
 * values.
 *
 * We are also given the list G, a subset of the values in the linked list.
 *
 * Return the number of connected components in G, where two values are
 * connected if they appear consecutively in the linked list.
 *
 * ======
 *
 * Source: Leetcode
 */
public class LinkedListComponents {
    public static class Solution {
        public int numComponents(ListNode head, int[] G) {
            Set<Integer> s = new HashSet<>();
            for (int i : G) s.add(i);
            int ans = 0;
            while (head != null) {
                if (s.contains(head.val) && (head.next == null || !s.contains(head.next.val)))
                    ans++;
                head = head.next;
            }
            return ans;
        }
    }
}