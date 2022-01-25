package linkedList;

import commons.ListNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * 1019
 */
public class NextGreaterNodeInLinkedList {
	class Solution {
		public int[] nextLargerNodes(ListNode head) {
			ArrayList<Integer> arr = new ArrayList<>();
			for (ListNode node = head; node != null; node = node.next)
				arr.add(node.val);
			int[] res = new int[arr.size()];
			Deque<Integer> d = new ArrayDeque<>();
			for (int i = 0; i < arr.size(); ++i) {
				while (!d.isEmpty() && arr.get(d.peek()) < arr.get(i))
					res[d.pop()] = arr.get(i);
				d.push(i);
			}
			return res;
		}
	}
}