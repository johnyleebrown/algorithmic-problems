package linkedList.other;

import util.ds.ListNode;

import java.util.HashSet;

/**
 * 142
 */
public class LinkedListCycleII
{
	/**
	 * Time complexity: O(n) Space complexity: O(n)
	 */
	public static ListNode solution1(ListNode head)
	{
		if (head == null) return null;

		ListNode pointer = head;
		HashSet<ListNode> set = new HashSet<>();
		while (set.add(pointer) && pointer != null)
		{
			pointer = pointer.next;
		}

		return pointer;
	}

	/**
	 * Find the meeting point Find the loop start by going from meeting point
	 * and from the head I-----I----I--- |_______| s = a + b f = 2 * (a + b) = a
	 * + b + cycle + b => a = c Time complexity: O(n) Space complexity: O(1)
	 */
	public static ListNode solution2(ListNode head)
	{
		ListNode slow = head;
		ListNode fast = head;

		while (fast != null && fast.next != null)
		{
			slow = slow.next;
			fast = fast.next.next;

			if (fast == slow)
			{
				ListNode slow2 = head;
				while (slow2 != slow)
				{
					slow = slow.next;
					slow2 = slow2.next;
				}
				return slow;
			}
		}

		return null;
	}
}