package linkedList;

import util.ds.ListNode;

/**
 * 2
 */
public class AddTwoNumbers {
	class Solution {
		public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
			if (l1 == null) return l2;
			if (l2 == null) return l1;
			int rem = 0;
			ListNode cur = null;
			ListNode res = null;
			while (!(l1 == null && l2 == null && rem == 0)) {
				int a = l1 == null ? 0 : l1.val;
				int b = l2 == null ? 0 : l2.val;
				int sum = a + b + rem;
				if (sum > 9) {
					rem = 1;
					sum = sum % 10;
				} else {
					rem = 0;
				}
				if (cur == null) {
					cur = new ListNode(sum);
					res = cur;
				} else {
					cur.next = new ListNode(sum);
					cur = cur.next;
				}
				if (l1 != null) l1 = l1.next;
				if (l2 != null) l2 = l2.next;
			}
			return res;
		}
	}
}