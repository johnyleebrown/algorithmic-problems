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
	class Solution0 {
		public ListNode removeNthFromEnd(ListNode head, int n) {
			if (head == null) return null;
			ListNode first = head, second = head;
			while (first != null)
			{
				if (n-- < 0) second = second.next;
				first = first.next;
			}
			if (n == 0) return head.next;
			second.next = second.next.next;
			return head;
		}
	}
}
