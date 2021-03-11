package linkedList.other;

import commons.ListNode;

/**
 * 2
 *
 * <p>======
 *
 * <p>Task.
 *
 * <p>You are given two non-empty linked lists representing two non-negative integers. The digits
 * are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers
 * and return the sum as a linked list.
 *
 * <p>You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * <p>Example 1:
 *
 * <p>Input: l1 = [2,4,3], l2 = [5,6,4] Output: [7,0,8] Explanation: 342 + 465 = 807. Example 2:
 *
 * <p>Input: l1 = [0], l2 = [0] Output: [0] Example 3:
 *
 * <p>Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9] Output: [8,9,9,9,0,0,0,1]
 *
 * <p>Constraints:
 *
 * <p>The number of nodes in each linked list is in the range [1, 100]. 0 <= Node.val <= 9 It is
 * guaranteed that the list represents a number that does not have leading zeros.
 *
 * <p>======
 *
 * <p>Source: Leetcode
 */
public class AddTwoNumbers {
  public static class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
      return add(l1, l2, 0);
    }

    private ListNode add(ListNode l1, ListNode l2, int rem) {
      boolean xx = l1 == null && l2 == null;
      if (xx && rem == 1) {
        return new ListNode(1);
      } else if (xx) {
        return null;
      }
      int l1val = l1 == null ? 0 : l1.val;
      int l2val = l2 == null ? 0 : l2.val;
      int sum = l1val + l2val + rem;
      ListNode ret = new ListNode(sum % 10);
      int newrem = sum > 9 ? 1 : 0;
      ListNode l1next = l1 == null ? l1 : l1.next;
      ListNode l2next = l2 == null ? l2 : l2.next;
      ret.next = add(l1next, l2next, newrem);
      return ret;
    }
  }
}
