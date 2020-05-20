package linkedList.other;

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
            helper(head);
            return head;
        }

        ListNode helper(ListNode cur) {
            // case 1
            if (cur == null) {
                return null;
            }

            // case 2 - end of line
            if (cur.next == null && cur.child == null) {
                return cur;
            }

            ListNode next = cur.next;

            // case 3 - have child - go there first
            if (cur.child != null) {
                ListNode last = helper(cur.child);

                // relink 1
                cur.next = cur.child;
                cur.child.prev = cur;
                cur.child = null;

                // relink 2
                if (next != null) {
                    last.next = next;
                    next.prev = last;
                }
            }

            // case 4 - go to next
            return helper(next);
        }
    }
}