package linkedList;

import commons.ListNode;

/**
 * 2
 */
public class AddTwoNumbers {

	public static class Solution {

		public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
			return add(l1, l2, 0);
		}

		private ListNode add(ListNode l1, ListNode l2, int rem) {
			boolean xx = l1 == null && l2 == null;
			if (xx && rem == 1) {
				return new ListNode(1);
			} else if (xx) {
				return null;
			}
			int l1val = l1 == null ? 0 : l1.val;
			int l2val = l2 == null ? 0 : l2.val;
			int sum = l1val + l2val + rem;
			ListNode ret = new ListNode(sum % 10);
			int newrem = sum > 9 ? 1 : 0;
			ListNode l1next = l1 == null ? l1 : l1.next;
			ListNode l2next = l2 == null ? l2 : l2.next;
			ret.next = add(l1next, l2next, newrem);
			return ret;
		}
	}
}
