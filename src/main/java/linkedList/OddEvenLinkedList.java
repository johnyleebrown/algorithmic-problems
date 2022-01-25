package linkedList;

import commons.ListNode;

/**
 * 328
 */
public class OddEvenLinkedList {
	private static class Solution {
		private ListNode firstEvenLink = null;

		public ListNode oddEvenList(ListNode cur) {
			return helper(true, cur, null);
		}

		private ListNode helper(boolean odd, ListNode cur, ListNode lastEven) {
			if (cur == null) {
				return null;
			}

			ListNode next = cur.next;

			if (!odd) {
				if (firstEvenLink == null && lastEven == null) {
					firstEvenLink = new ListNode(0);
					lastEven = cur;
					firstEvenLink.next = lastEven;
				} else {
					lastEven.next = cur;
					lastEven = cur;
				}

				if ((cur.next != null && cur.next.next == null)) {
					cur.next = null;
				}

				return helper(!odd, next, lastEven);
			} else {
				if (cur.next == null || cur.next.next == null) {
					helper(!odd, next, lastEven);
					if (firstEvenLink != null)
						cur.next = firstEvenLink.next;
				} else {
					cur.next = helper(!odd, next, lastEven);
				}

				return cur;
			}
		}
	}
}