package linkedList.other;

import commons.ListNode;

/**
 * 61
 */
public class RotateList {

	/**
	 * we need to connect the end to the first, return the new head, remove the
	 * cycle
	 */
	class Solution {

		public ListNode rotateRight(ListNode head, int k) {
			if (head == null) return null;
			// find length
			int len = 0;
			ListNode x = head;
			while (x != null) {
				len++;
				// connect end to head
				if (x.next == null) {
					x.next = head;
					x = x.next;
					break;
				}
				x = x.next;
			}
			// go to the future end
			int r = k % len;
			while (--len != r) {
				x = x.next;
			}
			// save the next, disconnect, return saved stf
			ListNode ans = x.next;
			x.next = null;
			return ans;
		}
	}
}