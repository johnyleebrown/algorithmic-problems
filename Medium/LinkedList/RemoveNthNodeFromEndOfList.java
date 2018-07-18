package Medium.LinkedList;

import Helpers.ListNode;

/**
 * 19
 *
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Could you do this in one pass?
 */
public class RemoveNthNodeFromEndOfList {
    /**
     * 75%
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    class Solution1 {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            if (head == null) return null;
            if (n == 0) return head;

            ListNode p = head;
            int size = 0;

            // get the size of a list
            while (p != null && p.next != null) {
                p = p.next.next;
                size += 2;
            }
            if (p != null) size++;

            if (n == size) return head.next;

            p = head;
            while (size != n + 1) {
                p = p.next;
                size--;
            }
            p.next = p.next.next;

            return head;
        }
    }
}
