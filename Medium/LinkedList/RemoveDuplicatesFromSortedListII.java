package Medium.LinkedList;

import Helpers.ListNode;

/**
 * 82
 *
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 */
public class RemoveDuplicatesFromSortedListII {
    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        if (head.next != null && head.val == head.next.val) return helper(head.val, head);
        else return helper(null, head);
    }

    ListNode helper(Integer prev, ListNode head) {
        boolean flag = head.next != null && head.val == head.next.val;
        if (head.next != null) head.next = helper(head.val, head.next);
        return prev != null && head.val == prev || flag ? head.next : head;
    }
}
