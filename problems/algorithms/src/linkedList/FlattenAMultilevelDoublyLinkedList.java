package linkedList;

import util.ds.ListNode;

/**
 * 430
 */
public class FlattenAMultilevelDoublyLinkedList
{
	class Solution
	{
		private ListNode prev = null;

		public ListNode flatten(ListNode head)
		{
			if (head == null)
				return null;
			return helper(head);
		}

		private ListNode helper(ListNode head)
		{
			if (head.next == null && head.child == null)
			{
				prev = head;
				return head;
			}

			if (head.child != null)
			{
				ListNode temp = head.next;

				head.next = helper(head.child);
				head.next.prev = head;
				head.child = null;

				if (prev != null)
					prev.next = temp;
				else
					prev = head;

				if (prev.next != null)
				{
					temp.prev = prev;
					helper(temp);
				}
			}
			else
				head.next = helper(head.next);

			return head;
		}
	}
}