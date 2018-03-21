package Medium.LinkedList;

import Helpers.Helper;
import Helpers.ListNode;

/**
 * 61
 *
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 */
public class RotateList {
    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public static ListNode solution1(ListNode head, int k) {
        if ((head == null && k == 0) || head == null) return null;
        if (k == 0) return head;

        ListNode pointer = head;
        ListNode last = null;
        int size = 0;

        while (pointer != null) {
            size++;
            last = pointer;
            pointer = pointer.next;
        }

        pointer = head;
        k %= size;

        int i = 0;
        while (i != size - k - 1) {
            i++;
            pointer = pointer.next;
        }

        last.next = head;
        head = pointer.next;
        pointer.next = null;

        return head;
    }

    /**
     * Fast runner, Slow runner
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public ListNode solution2(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;
        ListNode node = head;
        int size = 1;

        while (node.next != null) {
            size++;
            node = node.next;
        }

        k %= size;
        if (k == 0) return head;

        ListNode fast = head, slow = head;

        while (k-- > 0) {
            fast = fast.next;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        ListNode dummy = new ListNode(0);
        node = dummy;
        node.next = slow.next;
        slow.next = null;
        fast.next = head;

        return dummy.next;
    }

    public static void main(String[] args) {

        ListNode listNode = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        ListNode sol = solution1(listNode, 2);
        Helper.printList(sol);
    }
}
