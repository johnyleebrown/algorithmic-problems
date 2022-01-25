package linkedList;

import commons.ListNode;

/**
 * 19
 */
public class RemoveNthNodeFromEndOfList {

	class Solution0 {

		public ListNode removeNthFromEnd(ListNode head, int n) {
			if (head == null) return null;
			ListNode first = head, second = head;
			while (first != null) {
				if (n-- < 0)
					second = second.next;
				first = first.next;
			}
			if (n == 0)
				return head.next;
			second.next = second.next.next;
			return head;
		}
	}
}