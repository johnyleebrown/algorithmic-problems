package linkedList;

import commons.ListNode;

/**
 * 92
 */
public class ReverseLinkedListII {

	/**
	 * in recursive [0,n-1] we carried last one through the whole list
	 * now we will also carry first one at m to the end to connect with
	 * the rest of the list
	 * <p>
	 * how to reverse - when at a node - returning from recursion - set it's next pointer to
	 * self
	 */
	public static class Solution {

		public ListNode reverseBetween(ListNode head, int m, int n) {
			if (head == null) {
				return null;
			}
			if (head.next == null) {
				return head;
			}

			return h(head, m == 1 ? head : null, m - 1, n - 1);
		}

		private ListNode h(ListNode cur, ListNode first, int m, int n) {
			if (cur.next == null) {

				// if n is the last node - make sure to nullify the first
				first.next = null;
				return cur;
			}

			if (n == 0) {

				// if some node in the middle is n
				first.next = cur.next;
				return cur;
			}

			// save next because for 'first' node we will erase next pointer
			ListNode next = cur.next;
			ListNode last = h(cur.next, m - 1 == 0 ? cur.next : first, m - 1, n - 1);

			if (m <= 0) {

				// if we are from m to n - do the reversing
				next.next = cur;
				return last;
			}

			if (m == 1) {

				// if next node is start of reverse [1]->(2->3->4)->5
				cur.next = last;
			}

			// we want our regular head back if m is not 1
			return cur;
		}
	}
}