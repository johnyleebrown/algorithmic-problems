package linkedList.other;

import util.ds.ListNode;

/**
 * 25
 *
 * ======
 *
 * Task.
 *
 * Given a linked list, reverse the nodes of a linked list k at a time and
 * return its modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked
 * list. If the number of nodes is not a multiple of k then left-out nodes in
 * the end should remain as it is.
 *
 * ======
 *
 * Source: Leetcode
 */
public class ReverseNodesInKGroup
{
	/**
	 * Use reverse method to reverse parts of list, then combine the ends.
	 */
	public static class Solution
	{
		private int ind;

		public ListNode reverseKGroup(ListNode cur, int k)
		{
			ListNode ret = null;
			ListNode prevLast = null;

			while (cur != null)
			{
				ListNode curFirst = cur;
				ListNode curLast = getKthNode(cur, k);
				if (curLast == null)
				{
					prevLast.next = curFirst;
					return ret;
				}
				ListNode nextFirst = curLast.next;

				if (ret == null)
					ret = reverse(curFirst, nextFirst);
				else
					prevLast.next = reverse(curFirst, nextFirst);

				prevLast = curFirst;
				cur = nextFirst;
			}

			return ret;
		}

		private ListNode getKthNode(ListNode head, int k)
		{
			ListNode cur = head;
			int stopInd = ind + k - 1;
			while (cur != null && ind < stopInd)
			{
				cur = cur.next;
				ind++;
			}
			return cur;
		}

		private ListNode reverse(ListNode head, ListNode stopNode)
		{
			ListNode prev = null;
			ListNode cur = head;
			ListNode next = null;
			while (cur != stopNode)
			{
				next = cur.next;
				cur.next = prev;
				prev = cur;
				cur = next;
			}
			return prev;
		}

		public Solution()
		{
		}
	}
}
