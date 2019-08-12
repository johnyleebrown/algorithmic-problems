package Medium.LinkedList;

import java.util.HashMap;
import java.util.Map;

// 138
public class CopyListWithRandomPointer 
{
	class Solution 
	{
		public Node copyRandomList(Node head)
		{
			ListNode oldhead = head;
			ListNode newhead = null;
			
			// 1 step link old nodes to new nodes
			while (head != null)
			{
				Node newnode = new Node(head.val);
				
				if (newhead == null) newhead = newnode;
				
				newnode.random = head;
				newnode.next = head.next;
				head.next = newnode;
				head = newnode.next;
			}

			// 2 step link rand of new nodes
			head = oldhead; // at the new node right away
			while (head != null)
			{
				if (head.random != null) head.next.random = head.random.random.next;
				if (head.next != null) head = head.next.next;	
			}

			// 3 step unlink all
			// 1 - 1' - 2 - 2' - 3 - 3'
			// 1 - 2 - 3! 
			// 1' - 2' - 3'! 
			Node x = null;
			head = oldhead;
			while (head != null && head.next != null)
			{
				x = head.next;
				head.next = x.next;
				head = head.next;
				if (head != null) x.next = head.next;	
			}

			return newhead;
		}
	}
}
