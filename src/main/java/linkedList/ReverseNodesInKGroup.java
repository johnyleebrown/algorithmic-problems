package linkedList;

import commons.ListNode;

/**
 * 25
 */
public class ReverseNodesInKGroup {

	/**
	 * Use reverse method to reverse parts of list, then combine the ends.
	 */
	public static class Solution {

		private int ind;

		public Solution() {
		}

		public ListNode reverseKGroup(ListNode cur, int k) {
			ListNode ret = null;
			ListNode prevLast = null;

			while (cur != null) {
				ListNode curFirst = cur;
				ListNode curLast = getKthNode(cur, k);
				if (curLast == null) {
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

		private ListNode getKthNode(ListNode head, int k) {
			ListNode cur = head;
			int stopInd = ind + k - 1;
			while (cur != null && ind < stopInd) {
				cur = cur.next;
				ind++;
			}
			return cur;
		}

		private ListNode reverse(ListNode head, ListNode stopNode) {
			ListNode prev = null;
			ListNode cur = head;
			ListNode next = null;
			while (cur != stopNode) {
				next = cur.next;
				cur.next = prev;
				prev = cur;
				cur = next;
			}
			return prev;
		}
	}
}
