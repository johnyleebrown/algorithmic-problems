package linkedList.other;

import commons.ListNode;

/**
 * 206
 */
public class ReverseLinkedList {

	/**
	 * Recursive - pick nodes on the way back.
	 */
	public static class Solution {

		public ListNode reverseList(ListNode h) {
			if (h == null) {
				return null;
			}
			if (h.next == null) {
				return h;
			}
			ListNode first = reverseList(h.next);
			ListNode next = h.next;
			next.next = h;
			h.next = null;
			return first;
		}
	}

	/**
	 * Iterative.
	 */
	public static class Solution2 {

		public ListNode reverseList(ListNode head) {
			ListNode prev = null;
			ListNode cur = head;
			ListNode next = null;
			while (cur != null) {
				next = cur.next;
				cur.next = prev;
				prev = cur;
				cur = next;
			}
			return prev;
		}
	}
}