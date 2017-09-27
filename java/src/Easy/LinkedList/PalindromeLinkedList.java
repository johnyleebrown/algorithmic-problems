package Easy.LinkedList;

import Helpers.ListNode;

/**
 * 234
 * Given a singly linked list, determine if it is a palindrome.
 */
public class PalindromeLinkedList {

    // Get size, reverse half, compare halves
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        int size = getSize(head);
        if (size == 1) return true;
        int adjsize = size % 2 == 0 ? size/2 : size/2 + 1;

        ListNode prev = null;
        ListNode cur = head;
        ListNode next = null;
        while (adjsize != 0){
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
            adjsize--;
        }

        ListNode newhead = size % 2 == 0 ? prev : prev.next;

        while (newhead != null){
            if (newhead.val != cur.val) return false;
            newhead = newhead.next;
            cur = cur.next;
        }
        return true;
    }

    private int getSize(ListNode head){
        ListNode x = head;
        int l = 0;
        while (x != null){
            x = x.next;
            l++;
        }
        return l;
    }

    // ???
    class Solution {
        public class ListNode {
            int val;
            ListNode next;
            ListNode(int x) { val = x; }
        }

        public boolean isPalindrome(ListNode head) {
            if (head == null || head.next == null) return true;
            if (head.val == head.next.val && head.next.next == null) return true;
            ListNode slow = head;
            ListNode cur = head.next;
            while (cur.next != null) {
                if (slow.val == cur.next.val) {
                    if (cur.next.next != null) return false;
                    cur.next = null;
                    slow = slow.next;
                    cur = slow.next;
                    if (cur == null || slow.val == cur.val) return true;
                }
                else cur = cur.next;
            }
            return false;

        }
    }

    // Reverse half, compare halves
    class Solution2{
        public class ListNode {
            int val;
            ListNode next;
            ListNode(int x) { val = x; }
        }

        public ListNode reverse(ListNode head) {
            ListNode prev = null;
            while (head != null) {
                ListNode next = head.next;
                head.next = prev;
                prev = head;
                head = next;
            }
            return prev;
        }

        public boolean isPalindrome(ListNode head) {
            if (head == null || head.next == null) return true;
            ListNode fast = head.next;
            ListNode slow = head;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            slow = reverse(slow);
            while (head != null && slow != null) {
                if (head.val != slow.val) return false;
                head = head.next;
                slow = slow.next;
            }
            return true;
        }
    }
}
