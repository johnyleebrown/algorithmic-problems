package Easy.LinkedList;

import Helpers.ListNode;

/**
 * 21
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 */
public class MergeTwoSortedLists {
    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) return l2;
            if (l2 == null) return l1;
            if (l1.val < l2.val) {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeTwoLists(l1, l2.next);
                return l2;
            }
        }
    }

    class Solution2{
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode returnNode = new ListNode(-1);
            ListNode headNode = returnNode;
            while (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    returnNode.next = l1;
                    l1 = l1.next;
                } else {
                    returnNode.next = l2;
                    l2 = l2.next;
                }
                returnNode = returnNode.next;
            }
            if (l1 == null) returnNode.next = l2;
            else if (l2 == null) returnNode.next = l1;
            return headNode.next;
        }
    }
}
