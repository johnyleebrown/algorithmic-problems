package Medium.LinkedList;

import Helpers.ListNode;

public class SwapNodesInPairs 
{
	class Solution 
	{
		public ListNode swapPairs(ListNode head) 
		{
			if (head == null) return null;
			if (head.next == null) return head;
			
			ListNode x = head.next;
			ListNode y = head.next.next;
			head.next.next = head;
			head.next = swapPairs(y);
			
			return x;  
		}
	}
}

