package util.ds;

import java.util.Objects;

public class ListNode
{
	public int val;
	public ListNode random;
	public ListNode next, prev;
	public ListNode child;

	public ListNode(int x)
	{
		val = x;
	}

	public ListNode(String s)
	{
		ListNode root = buildFromStringArray(s);
		if (root == null)
			return;
		this.val = root.val;
		this.next = root.next;
	}

	private ListNode buildFromStringArray(String s)
	{
		String[] ar = s.split(",");
		int n = ar.length;
		ListNode root = getNodeWithValue(ar[0]);
		if (n == 0)
			return root;

		return root;
	}

	private ListNode getNodeWithValue(String s)
	{
		if ("null".equals(s))
			return null;
		else return new ListNode(Integer.parseInt(s));
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (!(o instanceof ListNode)) return false;
		ListNode listNode = (ListNode) o;
		return val == listNode.val &&
				Objects.equals(random, listNode.random) &&
				Objects.equals(next, listNode.next) &&
				Objects.equals(prev, listNode.prev) &&
				Objects.equals(child, listNode.child);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(val, random, next, prev, child);
	}

	private void print(ListNode head)
	{
		ListNode cur = head;
		while (cur != null)
		{
			System.out.print(cur.val + " ");
			cur = cur.next;
		}
	}

	private void printSelf(ListNode head)
	{
		System.out.println(head == null ? null : head.val);
	}
}
