package linkedList.other;

import util.ds.ListNode;

/**
 * 369
 *
 * ======
 *
 * Task.
 *
 * Given a non-negative integer represented as non-empty a singly linked list of
 * digits, plus one to the integer.
 *
 * You may assume the integer do not contain any leading zero, except the number
 * 0 itself.
 *
 * The digits are stored such that the most significant digit is at the head of
 * the list.
 *
 * ======
 *
 * Source: Leetcode
 */
public class PlusOneLinkedList {
    public static class Solution {
        public ListNode plusOne(ListNode head) {
            helper(head, head, true);
            return head;
        }

        boolean helper(ListNode head, ListNode cur, boolean flag) {
            if (cur == null) return false;
            if (cur.next == null) {
                if (cur.val == 9) {
                    if (cur == head) {
                        cur.val = 1;
                    } else {
                        cur.val = 0;
                    }
                    if (flag)
                        cur.next = new ListNode(0);
                    return true;
                } else {
                    cur.val++;
                    return false;
                }
            }
            boolean x = helper(head, cur.next, flag && cur.val == 9);
            if (x) {
                if (cur.val == 9) {
                    if (cur == head) {
                        cur.val = 1;
                    } else {
                        cur.val = 0;
                    }
                    return true;
                } else {
                    cur.val++;
                    return false;
                }
            }
            return false;
        }
    }
}