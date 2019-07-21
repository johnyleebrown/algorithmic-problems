package Easy.recursion;

import util.ListNode;

public class SwapNodesInPairs
{
    // swap 2 and 1, remember 3 first
    class Solution
    {
        public ListNode swapPairs(ListNode head)
        {
            // base cases
            if (head == null) return null;
            if (head.next == null) return head;

            ListNode node = head.next.next; // node=3
            ListNode n = head;
            head = head.next; // head=2
            head.next = n; // head.next=1
            if (head.next != null) head.next.next = swapPairs(node); //

            return head;
        }
    }
}
