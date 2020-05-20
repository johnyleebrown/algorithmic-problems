package linkedList.other;

import util.ds.ListNode;

/**
 * 725
 */
public class SplitLinkedListInParts
{
	class Solution
	{
		public ListNode[] splitListToParts(ListNode root, int k)
		{
			ListNode[] arr = new ListNode[k];
			if (root == null) return arr;
			int len = getLen(root);
			int x = len <= k ? 1 : len / k;
			int leftOver = len <= k ? 0 : len % k;

			for (int i = 0; i < k && root != null; i++)
			{
				arr[i] = root;
				int y = leftOver-- > 0 ? x + 1 : x;
				while (--y > 0) root = root.next;
				ListNode next = root.next;
				root.next = null;
				root = next;
			}

			return arr;
		}

		private int getLen(ListNode root)
		{
			int c = 0;
			while (root != null)
			{
				c++;
				root = root.next;
			}
			return c;
		}
	}
}