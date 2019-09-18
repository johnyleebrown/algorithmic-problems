/*
 * There is a set of pairs [i, j], i's and j's are unique on it's own.
 * Construct treap.
 *
 * Company:Google
 *
 * Reference
 * https://e-maxx.ru/algo/treap
 * https://leetcode.com/discuss/interview-question/363945/Google-or-Treap
 */
public class ConstructTreap
{
	/*
	 * TODO
	 * Sort by j, then construct as bst.
	 *
	 * Possible solution.
	 */
	public class S1()
	{
		public TreapNode buildTreapMaxHeap(int[][] nums) 
		{
			if (nums == null || nums.length == 0) return null;

			Arrays.sort(nums, ((o1, o2) -> o2[1] - o1[1]));

			final TreapNode root = new TreapNode(nums[0][0], nums[0][1]);

			for (int i = 1; i < nums.length; i++)
				insert(nums[i], root);

			return root;
		}

		// fill the node if it is empty
		private TreapNode insert(int[] e, final TreapNode root) 
		{
			if (root == null)
				return new TreapNode(e[0], e[1]);

			if (e[0] < root.val)
				root.left = insert(e, root.left);
			else
				root.right = insert(e, root.right);

			return root;

		}
	}
}

