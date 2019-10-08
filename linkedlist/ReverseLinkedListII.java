// 92
public class ReverseLinkedListII
{
	class Solution
	{
		public ListNode reverseBetween(ListNode head, int m, int n)
		{
			if (head == null) return null;
			return helper(head, m - 1, n - 1, null);
		}

		private ListNode helper(ListNode head, int m, int n, ListNode first)
		{
			if (n == 0)
			{
				if (first != null) first.next = head.next;
				return head;
			}

			if (m == 0 && first == null)
			{
				first = head;
			}

			if (m <= 0)
			{
				// saving it before cz in the last node we need to keep next
				ListNode y = head.next;
				ListNode x = helper(head.next, m - 1, n - 1, first);
				y.next = head;
				return x;
			}
			else
			{
				head.next = helper(head.next, m - 1, n - 1, first);
				return head;
			}
		}
	}
}

