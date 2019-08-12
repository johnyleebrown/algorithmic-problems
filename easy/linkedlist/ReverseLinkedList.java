package Easy.LinkedList;

import Helpers.ListNode;

// 206
public class ReverseLinkedList 
{
	// Recursive
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

	// Iterative
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
}

