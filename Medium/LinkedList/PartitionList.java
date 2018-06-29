package Medium.LinkedList;

import Helpers.ListNode;

/**
 * 86
 *
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes in each of the two partitions.
 */
public class PartitionList {
    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    class Solution {
        public ListNode partition(ListNode head, int x) {
            ListNode first = new ListNode(0);
            ListNode firstHead = first;
            ListNode second = new ListNode(0);
            ListNode secondHead = second;
            ListNode p = head;

            while (p != null) {
                if (p.val < x) {
                    first.next = p;
                    first = first.next;
                } else {
                    second.next = p;
                    second = second.next;
                }
                p = p.next;
            }

            second.next = null;
            first.next = null;

            first.next = secondHead.next;
            return firstHead.next;
        }
    }
}
