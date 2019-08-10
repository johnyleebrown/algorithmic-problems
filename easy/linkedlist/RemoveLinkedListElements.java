// 203
public class RemoveLinkedListElements
{
    public ListNode removeElements(ListNode head, int val) {
        ListNode newhead = null;
        ListNode prev = head;
        while (head != null)
        {
            while (head != null && head.val == val)
            {
                head = head.next;
            }
            if (newhead == null) newhead = head;
            if (prev.val != val && prev != head) prev.next = head;
            prev = head;
            if (head != null) head = head.next;
        }
        return newhead;
    }
}
/*
[1,2,6,3,4,5,6]
6
*/

