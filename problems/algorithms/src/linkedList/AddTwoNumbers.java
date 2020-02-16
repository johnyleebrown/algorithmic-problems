package linkedList;

import util.ds.ListNode;

/**
 * 2
 *
 * =====
 *
 * Tests.
 *
 * [9,9] [9,9] [1] [] [1,2] [] [1,2] [3] [] [] [1] [9,9]
 */
public class AddTwoNumbers
{
	/**
	 * sum up reversed digits, cuz otherwise we would have to find the len we
	 * can collect the digits, add, do a list creation, but we can do better
	 *
	 * we will iterate through the list creating a new list as we go but keeping
	 * the link to the prev element, so if we could add a leftover there
	 */
	class Solution
	{
		public ListNode addTwoNumbers(ListNode l1, ListNode l2)
		{
			int leftOver = 0;
			ListNode prev = null;
			ListNode root = null;

			while (l1 != null || l2 != null || leftOver != 0)
			{
				int one = 0;
				if (l1 != null)
				{
					one = l1.val;
					l1 = l1.next;
				}

				int two = 0;
				if (l2 != null)
				{
					two = l2.val;
					l2 = l2.next;
				}

				int sum = one + two + leftOver;
				int res = sum % 10;

				ListNode cur = new ListNode(res);
				if (root == null) root = cur;
				if (prev != null) prev.next = cur;
				prev = cur;

				leftOver = sum / 10;
			}

			return root;
		}
	}
}