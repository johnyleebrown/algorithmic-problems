package Medium.LinkedList;

import Helpers.ListNode;

/**
 * 24
 *
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 *
 * Your algorithm should use only constant extra space.
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 */
public class SwapNodesInPairs {
    /**
     * Intuitive recursion
     *
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    class Solution1 {
        public ListNode swapPairs(ListNode head) { // head - prev
            if (head == null || head.next == null) return head;
            ListNode cur = head.next;
            ListNode next = cur.next;
            head.next = next == null ? null : swapPairs(next);
            cur.next = head;
            return cur;
        }
    }
}
