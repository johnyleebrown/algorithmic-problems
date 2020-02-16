package linkedList;

import util.ds.ListNode;

/**
 * 328
 */
public class OddEvenLinkedList
{
	public ListNode oddEvenList(ListNode head)
	{
		if (head == null) return null;
		if (head.next == null) return head;
		return helper(head, null, head.next);
	}

	private ListNode helper(ListNode head, ListNode evenNode, ListNode evenHead)
	{
		if (evenNode != null)
		{
			evenNode.next = head.next;
			evenNode = evenNode.next;
		}
		else
		{
			evenNode = head.next;
		}

		if (head.next == null || head.next.next == null)
		{
			head.next = evenHead;
		}
		else
		{
			head.next = helper(head.next.next, evenNode, evenHead);
		}
		return head;
	}
}