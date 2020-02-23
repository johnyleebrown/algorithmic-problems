package recursion.other;

import util.ListNode;

// 206
public class ReverseLinkedList
{
    class Solution
    {
        public ListNode reverseList(ListNode head)
        {
            return helper(head, null);
        }

        // the idea is to always send to the recursion already ready 'next node'
        private ListNode helper(ListNode head, ListNode newNext)
        {
            if (head == null) return newNext;
            ListNode temp = head.next;
            head.next = newNext;
            return helper(temp, head);
        }
    }
}
