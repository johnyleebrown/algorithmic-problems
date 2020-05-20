package linkedList.other;

import util.ds.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 141
 */
public class LinkedListCycle
{
	class Solution1
	{
		public boolean hasCycle(ListNode head)
		{
			Set<ListNode> seen = new HashSet<>();
			while (head != null)
			{
				if (seen.contains(head)) return true;
				else seen.add(head);
				head = head.next;
			}
			return false;
		}
	}

	public class Solution2
	{
		public boolean hasCycle(ListNode head)
		{
			if (head == null)
			{
				return false;
			}

			ListNode fast = head;
			while (fast != null && fast.next != null)
			{
				if (fast.val == head.val)
				{
					return true;
				}

				head = head.next;
				fast = fast.next.next;
			}

			return false;
		}
	}
}