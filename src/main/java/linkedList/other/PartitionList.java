package linkedList.other;

import commons.ListNode;

/**
 * 86
 */
public class PartitionList {

	static class Solution {

		private ListNode next = null;
		private ListNode lastnext = null;

		public ListNode partition(ListNode head, int x) {
			ListNode otherhead = h(head, x);
			if (lastnext != null)
				lastnext.next = otherhead;
			return next == null ? otherhead : next;
		}

		public ListNode h(ListNode head, int x) {
			if (head == null)
				return null;

			ListNode res = h(head.next, x);

			if (head.val < x) {
				head.next = next;
				if (lastnext == null)
					lastnext = head;
				next = head;
				return res;
			}

			head.next = res;
			return head;
		}
	}
}