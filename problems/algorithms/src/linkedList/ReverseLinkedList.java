package linkedList;

import util.ds.ListNode;

/**
 * 206
 */
public class ReverseLinkedList
{
	class Solution1
	{
		public ListNode reverseList(ListNode head)
		{
			if (head == null || head.next == null) return head;
			ListNode temp = head.next;
			ListNode x = reverseList(head.next);
			head.next = null;
			temp.next = head;
			return x;
		}
	}

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

	class Solution3
	{
		public ListNode reverseList(ListNode head)
		{
			return helper(head, null);
		}

		// the idea is to always send to the recursion already ready 'next node'
		private ListNode helper(ListNode head, ListNode newNext)
		{
			if (head == null) return newNext;
			ListNode temp = head.next;
			head.next = newNext;
			return helper(temp, head);
		}
	}
}