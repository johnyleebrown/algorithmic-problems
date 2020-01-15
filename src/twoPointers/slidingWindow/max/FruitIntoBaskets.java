package twoPointers.slidingWindow.max;

/**
 * 904
 *
 * ======
 *
 * Task.
 *
 * In a row of trees, the i-th tree produces fruit with type tree[i].
 *
 * You start at any tree of your choice, then repeatedly perform the following steps:
 *
 * Add one piece of fruit from this tree to your baskets.  If you cannot, stop. Move to the next tree to the right of
 * the current tree.  If there is no tree to the right, stop. Note that you do not have any choice after the initial
 * choice of starting tree: you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you
 * stop.
 *
 * You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one
 * type of fruit each.
 *
 * What is the total amount of fruit you can collect with this procedure?
 *
 * ======
 *
 * Explanation.
 *
 * Find the largest subarray with exactly 2 types of elements.
 */
public class FruitIntoBaskets
{
	static class Solution
	{
		public int totalFruit(int[] tree)
		{
			int l = 0;
			int result = 0;
			int[] map = new int[tree.length];
			int uniqueNumbersInWindowCount = 0;

			for (int r = 0; r < tree.length; r++)
			{
				map[tree[r]]++;

				// if we ve found unique number - tree[r]
				if (map[tree[r]] == 1)
				{
					uniqueNumbersInWindowCount++;
				}

				while (uniqueNumbersInWindowCount > 2)
				{
					map[tree[l]]--;

					// if we ve found unique number - tree[r]
					if (map[tree[l]] == 0)
					{
						uniqueNumbersInWindowCount--;
					}

					l++;
				}
				result = Math.max(result, r - l + 1);
			}

			return result;
		}
	}
}
