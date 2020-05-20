package linkedList.other;

import util.ds.ListNode;

/**
 * 234
 */
public class PalindromeLinkedList
{
	class Solution
	{
		private boolean ans = true;

		public boolean isPalindrome(ListNode head)
		{
			if (head == null) return true;
			helper(head, head.next);
			return ans;
		}

		private ListNode helper(ListNode slow, ListNode fast)
		{
			if (fast == null) return slow.next;
			ListNode x = slow.next; // trick
			if (fast.next != null) x = helper(slow.next, fast.next.next);
			if (x.val != slow.val) ans = false;
			return x.next;
		}
	}
}