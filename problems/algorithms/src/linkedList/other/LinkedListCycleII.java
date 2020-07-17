package linkedList.other;

import util.ds.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 142
 */
public class LinkedListCycleII {
	/**
	 * Set.
	 */
	public static ListNode solution1(ListNode head) {
		if (head == null) return null;

		ListNode pointer = head;
		Set<ListNode> set = new HashSet<>();
		while (set.add(pointer) && pointer != null) {
			pointer = pointer.next;
		}

		return pointer;
	}

	/**
	 * 1: Check if has cycle
	 * 2*slow = fast
	 * slow=x+y
	 * fast=x+C*k+y,C*k - made k circle C times
	 * 2*(x+y)=x+C*k+y
	 * x=C*k-y
	 * 2: Find meeting point
	 * ? - need to find s2
	 * s2 - slow pointer from head
	 * s - slow pointer from meeting point
	 * s2=x
	 * s=(cur_position+x)%cycle_len, cur_position=y
	 * s=(y+C*k-y)%k=0
	 * meaning that after x steps position of s would be at the beginning of the
	 * cycle
	 */
	public static ListNode solution2(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;

			if (fast == slow) {
				ListNode slow2 = head;
				while (slow2 != slow) {
					slow = slow.next;
					slow2 = slow2.next;
				}
				return slow;
			}
		}

		return null;
	}
}