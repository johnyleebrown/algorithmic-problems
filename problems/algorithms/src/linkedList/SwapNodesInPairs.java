package linkedList;

import util.ds.ListNode;

/**
 * 24
 */
public class SwapNodesInPairs
{
	class Solution
	{
		public ListNode swapPairs(ListNode cur)
		{
			return traverse(null, cur);
		}

		private ListNode traverse(ListNode prev, ListNode cur)
		{
			if (cur == null)
				return null;
			if (prev == null && cur.next == null)
				return cur;

			ListNode next = cur.next;

			if (prev != null)
			{
				prev.next = traverse(null, next);
				cur.next = prev;
				return cur;
			}

			traverse(cur, next);
			return next;
		}
	}
}