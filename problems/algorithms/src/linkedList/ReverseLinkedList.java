package linkedList;

import util.ds.ListNode;

/**
 * 206
 */
public class ReverseLinkedList
{
	/**
	 * Recursive.
	 */
	class Solution
	{
		ListNode last;

		public ListNode reverseList(ListNode cur)
		{
			if (cur == null || cur.next == null)
			{
				last = cur;
				return cur;
			}
			ListNode r = reverseList(cur.next);
			cur.next = null;
			last.next = cur;
			last = cur;
			return r;
		}
	}

	/**
	 * Iterative.
	 */
	class Solution2
	{
		public ListNode reverseList(ListNode head)
		{
			ListNode prev = null;
			ListNode cur = head;
			ListNode next = null;
			while (cur != null)
			{
				next = cur.next;
				cur.next = prev;
				prev = cur;
				cur = next;
			}
			return prev;
		}
	}
}