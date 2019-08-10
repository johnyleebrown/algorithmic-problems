// 234
public class Palindrome Linked List
{
	// Recursive
	//
	// find the middle, go back recursively
	// 1 - 2 - 3 - 2 - 1
	// 1 - 2 - 2 - 1
	// 1 - 2
	class Solution {
		private boolean ans = true;
		public boolean isPalindrome(ListNode head) {
			if (head == null) return true;
			helper(head, head.next);
			return ans;
		}
		
		private ListNode helper(ListNode slow, ListNode fast)
		{
			if (fast == null) return slow.next;
			ListNode x = slow.next; // trick
			if (fast.next != null) x = helper(slow.next, fast.next.next);
			if (x.val != slow.val) ans = false;
			return x.next;
		}
		
	/* before
		private ListNode helper(ListNode slow, ListNode fast)
		{
			if (fast == null || fast.next == null) return slow.next;
			ListNode x = helper(slow.next, fast.next.next);
			if (x.val != slow.val) ans = false;
			return x.next;
		}
	*/
	}

	

}

