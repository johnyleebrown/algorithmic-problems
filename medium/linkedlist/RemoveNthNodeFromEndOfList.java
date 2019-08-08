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

    /**
     * One pass
     * Idea is super simple: keep the distance btw
     * 2 pointers n and get the faster one to the end
     *
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    class Solution2 {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode headNode = new ListNode(0);
            headNode.next = head;
            ListNode fast = headNode;
            ListNode slow = headNode;

            while (fast.next != null) {
                if (n <= 0) slow = slow.next;
                fast = fast.next;
                n--;
            }
            if (slow.next != null) slow.next = slow.next.next;

            return headNode.next;
        }
    }
}
/*
[1,2,3,4,5]
1
[1,2,3,4,5]
2
[1,2,3,4,5]
3
[1,2,3,4,5]
4
[1,2,3,4,5]
5
[1,2,3,4]
1
[1,2,3,4]
2
[1,2,3,4]
3
[1,2,3,4]
4
[1,2,3,4]
5
 */