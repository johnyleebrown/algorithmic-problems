package medium.linkedlist;
import util.*;
public class RemoveNthNodeFromEndOfListTest
{
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        ListNode first = head, second = head;
        while (first != null)
        {
            if (n < 0) second = second.next;
            first = first.next;
            n--;
        }
        if (n == 0) return head.next;
        second.next = second.next.next;
        return head;
    }
	public static void main(String[] args)
	{
		ListNode head = new ListNode(1);
		ListNode head2 = new ListNode(2);
		ListNode head3 = new ListNode(3);
		head.next = head2;
		head2.next = head3;
		int n = 1;
		ListNode x = removeNthFromEnd(head3, n);
		System.out.println();
		while (x != null)
		{
			System.out.print(x.val + " ");
			x = x.next;
		}
		System.out.println();
	}
}

