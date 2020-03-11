package tree.validate;

import util.tester.Tester;

/**
 * 1361
 *
 * ======
 *
 * Task.
 *
 * You have n binary tree nodes numbered from 0 to n - 1 where node i has two
 * children leftChild[i] and rightChild[i], return true if and only if all the
 * given nodes form exactly one valid binary tree.
 *
 * If node i has no left child then leftChild[i] will equal -1, similarly for
 * the right child.
 *
 * Note that the nodes have no values and that we only use the node numbers in
 * this problem.
 *
 * ======
 *
 * Source: Leetcode
 */
public class ValidateBinaryTreeNodes
{
	/**
	 * Check if every node has 1 parent, except root.
	 */
	private static class Solution
	{
		public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild)
		{
			boolean[] hasParents = new boolean[n];

			// case - so no node has a root as child
			hasParents[0] = true;

			// case - check if we haven't already set a parent
			// which means we would have 2 parents for 1 node
			for (int i = 0; i < n; i++)
			{
				if (leftChild[i] != -1)
				{
					if (hasParents[leftChild[i]])
						return false;

					hasParents[leftChild[i]] = true;
				}

				if (rightChild[i] != -1)
				{
					if (hasParents[rightChild[i]])
						return false;

					hasParents[rightChild[i]] = true;
				}
			}

			// case - everyone has a parent
			// otherwise we would have > than 1 connected component
			for (int i = 0; i < n; i++)
				if (!hasParents[i])
					return false;

			return true;
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add(4, new int[]{1, -1, 3, -1}, new int[]{2, -1, -1, -1}).expect(true)
				.add(4, new int[]{1, -1, 3, -1}, new int[]{2, 3, -1, -1}).expect(false)
				.add(2, new int[]{1, 0}, new int[]{-1, -1}).expect(false)
				.add(6, new int[]{1, -1, -1, 4, -1, -1}, new int[]{2, -1, -1, 5, -1, -1}).expect(false)
				.run();
	}
}
