// 430
public class FlattenAMultilevelDoublyLinkedList
{
	class Solution 
	{
		private Node prev = null;
		
		public Node flatten(Node head) {
			if (head == null) return null;
			return helper(head);
		}
		
		private Node helper(Node head)
		{
			if (head.next == null && head.child == null) 
			{
				prev = head;
				return head;
			}
			
			if (head.child != null) 
			{
				Node temp = head.next;
				
				head.next = helper(head.child);
				head.next.prev = head;
				head.child = null;
				
				if (prev != null) prev.next = temp;
				else prev = head;
				
				if (prev.next != null)
				{    
					temp.prev = prev;
					helper(temp);
				}
			}
			else head.next = helper(head.next);
			
			return head;
		}
	}
/*
Input:
 1---2---3---4---5---6--NULL
		 |
		 7---8---9---10--NULL
			 |
			 11--12--NULL

Output:
1-2-3-7-8-11-12-9-10-4-5-6-NULL

Input:
 1
 |
 7
 |
 11
 |
 NULL
 
 Output:
 1 - 7 - 11 - NULL
*/
}

