package linkedList.other;

import commons.ListNode;

/**
 * 445
 */
public class AddTwoNumbersII {

	/**
	 * - Get lengths of lists to know the offset to align heads
	 * - From ends do sum op, leftover can be calculated after the new node is returned
	 */
	public static class Solution1 {
		public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
			int len1 = calcLength(l1), len2 = calcLength(l2);
			int d = Math.abs(len1 - len2);
			// sending in 3rd param a longer list of 2
			return len1 > len2 ? h(d, d, l1, l2) : h(d, d, l2, l1);
		}

		/**
		 * Assuming 3rd param is a bigger list
		 */
		ListNode h(int base, int offset, ListNode l1, ListNode l2) {

			if (l1 == null) return null;

			ListNode next = h(base, offset - 1, l1.next, offset <= 0 ? l2.next : l2);

			// calc leftover from new next node
			int leftover = 0;
			if (next != null && next.val > 9) {
				leftover = 1;
				next.val %= 10;
			}

			int smaller = offset > 0 ? 0 : l2.val;
			int val = l1.val + smaller + leftover;
			ListNode ret = new ListNode(val);
			ret.next = next;

			if (base == offset && ret.val > 9) {
				ListNode extra = new ListNode(1);
				ret.val %= 10;
				extra.next = ret;
				return extra;
			}

			return ret;
		}

		int calcLength(ListNode l) {
			int ret = 0;
			while (l != null) {
				ret++;
				l = l.next;
			}
			return ret;
		}
	}

	/**
	 * For small numbers.
	 */
	public static class Solution2 {
		public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
			long i = 0;
			while (l1 != null) {
				i = i * 10 + l1.val;
				l1 = l1.next;
			}
			long j = 0;
			while (l2 != null) {
				j = j * 10 + l2.val;
				l2 = l2.next;
			}
			long res = i + j;
			if (res == 0) return new ListNode(0);
			ListNode prev = null;
			while (res != 0) {
				long x = res % 10;
				ListNode n = new ListNode((int) x);
				n.next = prev;
				prev = n;
				res /= 10;
			}
			return prev;
		}
	}
}