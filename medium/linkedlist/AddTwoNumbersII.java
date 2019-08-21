// 445
public class AddTwoNumbersII
{
	class Solution 
	{
		private int leftOver = 0;

		public ListNode addTwoNumbers(ListNode l1, ListNode l2) 
		{
			int c1 = getLen(l1), c2 = getLen(l2);
			if (c1 == 0) return l2; if (c2 == 0) return l1;
			ListNode ans = sum1(c1 > c2 ? l1 : l2, c1 > c2 ? l2 : l1, Math.abs(c1-c2));
			if (leftOver != 0) 
			{
				ListNode head = new ListNode(1);
				head.next = ans;
				return head;
			}
			return ans;
		}

		// for parts where we have either l1 or l2 elements
		private ListNode sum1(ListNode lx, ListNode lother, int c)
		{
			if (lx == null) return null;
			if (c == 0) return sum2(lx, lother);
			ListNode y = sum1(lx.next, lother, c - 1);
			int x = lx.val + leftOver;
			leftOver = x / 10;
			ListNode head = new ListNode(x % 10);
			head.next = y;
			return head;
		}

		// for parts where we have l1 and l2 elements
		private ListNode sum2(ListNode l1, ListNode l2)
		{
			if (l1 == null && l2 == null) return null;
			ListNode y = sum2(l1.next, l2.next);
			int x = l1.val + l2.val + leftOver;
			leftOver = x / 10;
			ListNode head = new ListNode(x % 10);
			head.next = y;
			return head;
		}

		private int getLen(ListNode l1)
		{
			int c = 0; while (l1!=null) { c++; l1 = l1.next; } return c;
		}
	}

}

