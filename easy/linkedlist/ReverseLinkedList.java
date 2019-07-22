package Easy.LinkedList;

import Helpers.ListNode;

/**
 * 206
 * Company: Yelp
 *
 * Reverse Linked List
 */
public class ReverseLinkedList {

    // Time complexity : O(n) ; Space complexity : O(1)
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }

        return prev;
    }

    // Time complexity : O(n). Space complexity : O(n).
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

	class Solution {
	    public ListNode reverseList(ListNode head) {
			return helper(head, null);
		}

		// the idea is to always send to the recursion already ready 'next node'
		private ListNode helper(ListNode head, ListNode newNext)
		{
			if (head == null) return newNext;
			ListNode temp = head.next;
			head.next = newNext;
			return helper(temp, head);
		}
	}
}
