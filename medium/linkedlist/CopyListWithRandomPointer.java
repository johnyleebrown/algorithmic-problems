package Medium.LinkedList;

import java.util.HashMap;
import java.util.Map;

// 138
public class CopyListWithRandomPointer {
	// 1st solution - use map
	// 2nd sol, but there we change origin next links, so this don't count
	// 3nd mod - next pointer of the or node is the new node
	class Solution 
	{
		public Node copyRandomList(Node head) 
		{
			// go through the list, set origin nexts to new nodes, set new nodes rands to origins
			
			Node newHead = null;
			Node newPrev = null;
			
			Node x = head;
			while (x != null)
			{
				// saved the next so we could go there
				Node temp = x.next;
				
				// create a new node
				Node newNode = new Node(x.val);
				// if there is a prev defined, link it to a new node
				if (newPrev != null) newPrev.next = newNode;
				// if there is no head yet, assign one
				if (newHead == null) newHead = newNode;
				
				// origin next is new node
				x.next = newNode;
				// new rand is origin node
				newNode.random = x;
				
				// going there
				x = temp;
			}
			
			// go throught the origin again and use rands
			x = newHead;
			while (x != null)
			{   
				Node newN = x.random.random.next;
				x.random = newN;
				x = x.next;
			}
			
			return newHead;
		}
		
	}
}
