package math.random;

import commons.ListNode;

import java.util.Random;

/**
 * 382
 */
public class LinkedListRandomNode {

	static class Solution {

		private ListNode head;
		private Random r;

		public Solution(ListNode head) {
			this.head = head;
			r = new Random();
		}

		public int getRandom() {
			int result = 0;
			int countNodes = 0;
			ListNode cur = head;

			while (cur != null) {
				countNodes++;

				if (r.nextInt(countNodes) == 0) {
					result = head.val;
				}

				cur = cur.next;
			}

			return result;
		}
	}
}