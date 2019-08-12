package easy.linkedlist;

import util.ListNode;

public class Test {
    public static ListNode reverseList(ListNode head) {
        if (head == null) return null;
        //System.out.println(head.val);
        if (head.next == null) return head;
        ListNode temp = head.next;
        ListNode x = reverseList(head.next);
        //System.out.println(temp.val + " " + head.val);
        head.next = null;
		temp.next = head;
        return x;
    }
	public static void main(String[] args)
	{
		ListNode head = new ListNode(1);
		ListNode head2 = new ListNode(2);
		ListNode head3 = new ListNode(3);
		ListNode head4 = new ListNode(4);
		ListNode head5 = new ListNode(5);
		head.next = head2;
		head2.next = head3;
		head3.next = head4;
		head4.next = head5;
		
		head = reverseList(head);
		while (head != null)
		{
			System.out.println(head.val);
			head = head.next;
		}
	}
}
