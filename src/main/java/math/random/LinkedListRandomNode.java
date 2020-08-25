package math.random;

import util.ds.ListNode;

import java.util.Random;

/**
 * 382
 *
 * =====
 *
 * Task.
 *
 * Given a singly linked list, return a random node's value from the linked
 * list. Each node must have the same probability of being chosen.
 *
 * Follow up: What if the linked list is extremely large and its length is
 * unknown to you? Could you solve this efficiently without using extra space?
 */
public class LinkedListRandomNode
{
	class Solution
	{
		private ListNode head;
		private Random r;

		public Solution(ListNode head)
		{
			this.head = head;
			r = new Random();
		}

		public int getRandom()
		{
			int result = 0;
			int countNodes = 0;
			ListNode cur = head;

			while (cur != null)
			{
				countNodes++;

				if (r.nextInt(countNodes) == 0)
				{
					result = head.val;
				}

				cur = cur.next;
			}

			return result;
		}
	}
}