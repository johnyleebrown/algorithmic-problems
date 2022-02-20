package linkedList.other;

import commons.ListNode;

/**
 * 876
 */
public class MiddleOfTheLinkedList {

	static class Solution {

		public ListNode middleNode(ListNode head) {
			ListNode runner = head;
			while (runner != null && runner.next != null) {
				head = head.next;
				runner = runner.next.next;
			}

			return head;
		}
	}
}