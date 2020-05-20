package linkedList.other;

import util.ds.ListNode;

/**
 * 203
 */
public class RemoveLinkedListElements
{
	// recursive
	class Solution
	{
		public ListNode removeElements(ListNode head, int val)
		{
			if (head == null) return null;
			head.next = removeElements(head.next, val);
			return head.val == val ? head.next : head;
		}
	}

	// iterative
	class Solution2
	{
		public ListNode removeElements(ListNode head, int val)
		{
			ListNode newhead = null;
			ListNode prev = head;

			while (head != null)
			{
				while (head != null && head.val == val)
				{
					head = head.next;
				}

				if (newhead == null)
				{
					newhead = head;
				}

				if (prev.val != val && prev != head)
				{
					prev.next = head;
				}

				prev = head;

				if (head != null)
				{
					head = head.next;
				}
			}

			return newhead;
		}
	}
}