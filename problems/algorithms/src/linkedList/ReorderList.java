package linkedList;

import util.ds.ListNode;

/**
 * 143
 */
public class ReorderList
{
	class Solution
	{
		private boolean even = true;

		public void reorderList(ListNode head)
		{
			if (head == null) return;
			// find the length
			int len = getLen(head);
			int n = len / 2;
			if (len % 2 != 0)
			{
				even = false;
				n = len / 2 + 1;
			}
			helper(head, n - 1);
		}

		private ListNode helper(ListNode head, int n)
		{
			// base - return the next when we reached the middle of the list
			if (n == 0)
			{
				if (even)
				{
					ListNode a = head.next.next;
					head.next.next = null;
					return a;
				}
				else
				{
					ListNode a = head.next;
					head.next = null;
					return a;
				}
			}

			// node that was returned should point to the head next
			ListNode x = helper(head.next, n - 1);
			ListNode y = x.next;
			x.next = head.next;
			head.next = x;
			return y;
		}

		private int getLen(ListNode head)
		{
			int c = 0;
			while (head != null)
			{
				c++;
				head = head.next;
			}
			return c;
		}
	}
}