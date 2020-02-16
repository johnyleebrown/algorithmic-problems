package linkedList;

import util.ds.ListNode;

/**
 * 83
 */
public class RemoveDuplicatesFromSortedList
{
	class Solution1
	{
		public ListNode deleteDuplicates(ListNode head)
		{
			if (head == null || head.next == null)
				return head;
			head.next = deleteDuplicates(head.next);
			return head.val == head.next.val ? head.next : head;
		}
	}

	class Solution2
	{
		public ListNode deleteDuplicates(ListNode head)
		{
			ListNode x = head;
			while (x != null && x.next != null)
			{
				if (x.next.val == x.val)
					x.next = x.next.next;
				else
					x = x.next;
			}
			return head;
		}
	}
}