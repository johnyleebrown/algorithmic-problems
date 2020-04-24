package linkedList;

import util.ds.ListNode;

/**
 * 430
 */
public class FlattenAMultilevelDoublyLinkedList {
    /**
     * Just go down case by case. Maybe the tricky part is not to forget the
     * prev links.
     */
    public static class Solution {
        public ListNode flatten(ListNode head) {
            traverse(head);
            return head;
        }

        private ListNode traverse(ListNode cur) {
            if (cur == null) return null;
            if (cur.next == null && cur.child == null) {
                return cur;
            }
            if (cur.next == null) {
                ListNode x = traverse(cur.child);
                cur.next = cur.child;
                cur.next.prev = cur;
                cur.child = null;
                return x;
            }
            ListNode next = cur.next;
            if (cur.child != null) {
                ListNode x = traverse(cur.child);
                x.next = next;
                next.prev = x;
                cur.next = cur.child;
                cur.next.prev = cur;
                cur.child = null;
            }
            return traverse(next);
        }
    }
}