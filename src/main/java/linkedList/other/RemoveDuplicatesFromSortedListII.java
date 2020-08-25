package linkedList.other;

import util.ds.ListNode;

/**
 * 82
 */
public class RemoveDuplicatesFromSortedListII
{
	/**
	 * We check 2 things: if next is the same as cur, for 0..n-1 of the
	 * sequence, and if prev is the same as cur, for the 1..n.
	 */
	class Solution
	{
		public ListNode deleteDuplicates(ListNode cur)
		{
			return h(cur, null);
		}

		private ListNode h(ListNode cur, ListNode prev)
		{
			if (cur == null)
				return null;

			ListNode ret = h(cur.next, cur);

			if (prev != null && cur.val == prev.val || cur.next != null && cur.next.val == cur.val)
				return ret;

			cur.next = ret;
			return cur;
		}
	}
}