package Medium.LinkedList;

import java.util.HashSet;

import Helpers.ListNode;

/**
 * 142
 *
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *
 * Note: Do not modify the linked list.
 *
 * Follow up:
 * Can you solve it without using extra space?
 */
public class LinkedListCycleII {
    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static ListNode solution1(ListNode head) {
        if (head == null) return null;

        ListNode pointer = head;
        HashSet<ListNode> set = new HashSet<>();
        while (set.add(pointer) && pointer != null) {
            pointer = pointer.next;
        }

        return pointer;
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
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

    public static void main(String[] args) {

        ListNode listNode = new ListNode(1);
//        ListNode listNode2 = new ListNode(2);
//        ListNode listNode3 = new ListNode(3);
//        ListNode listNode4 = new ListNode(4);
//        ListNode listNode5 = new ListNode(5);
//        listNode.next = listNode2;
//        listNode2.next = listNode3;
//        listNode3.next = listNode4;
//        listNode4.next = listNode5;
//        listNode5.next = listNode3;
        ListNode sol = solution1(listNode);
        System.out.println(sol == null);
//        Helper.printList(sol);
    }
}
