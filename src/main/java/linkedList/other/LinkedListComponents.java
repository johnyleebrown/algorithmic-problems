package linkedList.other;

import commons.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 817
 */
public class LinkedListComponents {

	public static class Solution {

		public int numComponents(ListNode head, int[] G) {
			Set<Integer> s = new HashSet<>();
			for (int i : G) {
				s.add(i);
			}
			int ans = 0;
			while (head != null) {
				if (s.contains(head.val) && (head.next == null || !s.contains(head.next.val)))
					ans++;
				head = head.next;
			}
			return ans;
		}
	}
}