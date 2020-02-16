package linkedList;

import util.ds.ListNode;

/**
 * 82
 */
public class RemoveDuplicatesFromSortedListII
{
	class Solution
	{
		public ListNode deleteDuplicates(ListNode head)
		{
			return helper(head, null);
		}

		private ListNode helper(ListNode head, ListNode prev)
		{
			if (head == null) return null;
			head.next = helper(head.next, head);
			if (head.next != null)
			{
				if (head.val == head.next.val)
				{
					if ((prev == null) ||
							(prev != null && prev.val != head.val))
						return head.next.next;
					else return head.next;
				}
			}
			return head;
		}
	}
}