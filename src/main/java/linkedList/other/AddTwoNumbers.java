package linkedList.other;

import commons.ListNode;

/**
 * 2
 */
public class AddTwoNumbers {
	public static class Solution {
		public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
			return f(l1, l2, 0);
		}

		ListNode f(ListNode l1, ListNode l2, int rem) {
			if (l1 == null && l2 == null) {
				if (rem == 1) return new ListNode(1);
				else return null;
			}
			if (l1 == null && rem == 0) return l2;
			else if (l2 == null && rem == 0) return l1;
			int val = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + rem;
			ListNode ret = new ListNode(val % 10);
			ret.next = f(l1 == null ? null : l1.next, l2 == null ? null : l2.next, val < 10 ? 0 : 1);
			return ret;
		}
	}
}