package tree.count;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Google_Interview_7
 */
public class MinPathBetweenLeafNodes
{
	private static int globalMin;

	// N - ary tree node
	private static class TN
	{
		private int val;
		private List<TN> children;

		TN(int value)
		{
			val = value;
			children = new ArrayList<>();
		}
	}

	/**
	 * if the child is 1 then we compare with with the last that came from
	 * recursion
	 *
	 * if it is not, that means we already compared and we get the last one that
	 * we will send further
	 *
	 * we need to return a last one at each crossroads we need to update global
	 * minimum at each crossroads
	 */
	private static int[] calculate(TN root)
	{
		if (root.children.size() == 0)
			return new int[]{root.val, root.val};

		int[] base = calculate(getEl(root, 0));
		int prev = base[1];
		int localFirst = base[0];

		for (int i = 1; i < root.children.size(); i++)
		{
			int[] cur = calculate(getEl(root, i));
			int first = cur[0], last = cur[1];

			updateMin(prev + root.val + first);
			prev = last;
		}

		return new int[]{localFirst + root.val, prev + root.val};
	}

	private static void updateMin(int val)
	{
		globalMin = Math.min(globalMin, val);
	}

	private static TN getEl(TN root, int i)
	{
		return root.children.get(i);
	}

	private static int findMin(TN root)
	{
		if (root == null) return -1;
		calculate(root);
		return globalMin == Integer.MAX_VALUE ? -1 : globalMin;
	}

	public static void main(String[] args)
	{
		System.out.println();
		globalMin = Integer.MAX_VALUE;
		System.out.println(findMin(test1()));
//		globalMin = Integer.MAX_VALUE;
//		System.out.println(findMin(test2()));
//		globalMin = Integer.MAX_VALUE;
//		System.out.println(findMin(test3()));
	}

	private static TN test1()
	{
		TN tn1 = new TN(1);
		TN tn100 = new TN(10);
		TN tnm2 = new TN(-2);

		TN tn4 = new TN(4);
		tn4.children.add(tn1);
		tn4.children.add(tn100);
		tn4.children.add(tnm2);

		TN tn11 = new TN(1);
		TN tnm10 = new TN(-10);

		TN tn2 = new TN(2);
		tn2.children.add(tnm10);
		tn2.children.add(tn11);
		tn2.children.add(tn4);

		TN tn7 = new TN(7);

		TN root = new TN(5);
		root.children.add(tn2);
		root.children.add(tn7);

		Queue<TN> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty())
		{
			int size = q.size();
			System.out.println();
			while(--size>=0)
			{
				TN cur = q.poll();
				System.out.print(cur.val + " " + " ch "+cur.children.size()+" | ");
				for (TN c:cur.children)
				{
					q.add(c);
				}
			}
		}
		System.out.println();
		return root;
	}

	private static TN test2()
	{
		TN tn1 = new TN(1);
		TN tn100 = new TN(10);
		TN tnm2 = new TN(-2);

		TN tn4 = new TN(4);
		tn4.children.add(tn1);
		tn4.children.add(tn100);
		tn4.children.add(tnm2);

		TN tn11 = new TN(1);
		TN tn10 = new TN(10);

		TN tn2 = new TN(2);
		tn2.children.add(tn10);
		tn2.children.add(tn11);
		tn2.children.add(tn4);

		TN tn7 = new TN(7);

		TN root = new TN(5);
		root.children.add(tn2);
		root.children.add(tn7);

		return root;
	}

	private static TN test3()
	{
		TN tn1 = new TN(1);
		TN tn100 = new TN(10);
		TN tnm2 = new TN(-2);

		TN tn4 = new TN(4);
		tn4.children.add(tn1);
		tn4.children.add(tn100);
		tn4.children.add(tnm2);

		TN tn11 = new TN(1);
		TN tn10 = new TN(10);

		TN tn2 = new TN(2);
		tn2.children.add(tn10);
		tn2.children.add(tn11);
		tn2.children.add(tn4);

		TN tnm7 = new TN(-7);

		TN root = new TN(5);
		root.children.add(tn2);
		root.children.add(tnm7);

		return root;
	}
}