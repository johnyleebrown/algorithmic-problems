package Easy.LinkedList;

import java.util.HashSet;
import java.util.Set;

import Helpers.ListNode;

// 141
public class LinkedListCycle 
{
    // O(n) ; O(n)
    class Solution1 {
        public boolean hasCycle(ListNode head) {
            Set<ListNode> seen = new HashSet<>();
            while (head != null) {
                if (seen.contains(head)) return true;
                else seen.add(head);
                head = head.next;
            }
            return false;
        }
    }

    // O(n) ; O(1)
    public class Solution 
	{
        public boolean hasCycle(ListNode head) 
		{
			if (head == null) return false;
			ListNode fast = head;
			while (head != null && fast.next != null)
			{
				if (fast.val == head.val) return true;
				head = head.next;
				fast = fast.next.next;
			}
			return false;
        }
    }
}
