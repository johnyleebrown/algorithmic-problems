package Easy.LinkedList;

import java.util.HashSet;
import java.util.Set;

/**
 * 141
 * Given a linked list, determine if it has a cycle in it.
 */
public class LinkedListCycle {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    // O(n) ; O(n)
    class Solution1 {
        public boolean hasCycle(ListNode head) {
            Set<ListNode> seen = new HashSet<>();
            while (head != null) {
                if (seen.contains(head)) return true;
                else seen.add(head);
                head = head.next;
            }
            return false;
        }
    }

    // O(n) ; O(1)
    public class Solution {
        public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null) return false;
            ListNode fast = head.next;
            ListNode slow = head;
            while (fast != null && fast.next != null) {
                if (fast == slow) return true;
                fast = fast.next.next;
                slow = slow.next;
            }
            return false;
        }
    }
}
