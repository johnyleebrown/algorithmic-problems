/*
 * Merge k sorted arrays with same length.
 * Company: Amazon
 */
public class MergeKSortedArrays
{
	/*
	 * Merge pairs.
	 * O(nklog(k))
	 */
	public class Solution1
	{
		public int[] mergeKSortedArrays(int[][] a)
		{
			int n = a.length, m = a[0].length; 
			int[] merged = new int[n * m];
			
			// preparations
			// migrate all data to queue of lists

			while (q.size() > 1)
			{
				int size = q.size();
				while (--size >= 0)
				{
					List<Integer> l1 = q.poll();
					List<Integer> l2 = q.poll();
					q.add(merge(l1, l2));
				}
			}
			
			return merged;
		}
	}
}

