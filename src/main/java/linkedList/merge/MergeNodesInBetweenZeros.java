package linkedList.merge;

import commons.ListNode;

/**
 * 2181
 */
public class MergeNodesInBetweenZeros {

	/**
	 * SF
	 */
	public static class Solution {

		ListNode first;

		public ListNode mergeNodes(ListNode head) {
			helper(head);
			return first;
		}

		private int helper(ListNode head) {
			if (head.next.next == null) {
				return head.val;
			}
			int sum = helper(head.next);
			if (head.val == 0) {
				ListNode node = new ListNode(sum);
				node.next = first;
				first = node;
				return 0;
			} else {
				return sum + head.val;
			}

		}
	}
}
