// 23
public class MergeKSortedLists
{
	// have a counter and collect integers from all the lists in order
	class Solution {
		// [[1,4,5],[1,3,4],[2,6]]
		// [1,1,2,3,6], should be [1,1,2,3,4,4,5,6]
		public ListNode mergeKLists(ListNode[] lists) 
		{
			ListNode newhead = null;
			ListNode prev = null;
			boolean isNotEmpty = true;
			int min = Integer.MAX_VALUE;
			int size = 0;
			
			for (ListNode n: lists)
			{
				size++;
				if (n != null && min > n.val)
				{
					min = n.val;
					newhead = n;
					prev = n;
				}
			}
			
			while (isNotEmpty && min != Integer.MAX_VALUE)
			{
				int newmin = Integer.MAX_VALUE;
				for (int i = 0; i < size; i++)
				{
					ListNode n = lists[i];
					isNotEmpty = false || isNotEmpty;
					while (n != null && n.val == min && n != prev)
					{
						isNotEmpty = true;
						prev.next = n;
						prev = n;
						lists[i] = lists[i].next;
					}
					if (n != null && n.val > min) newmin = Math.min(newmin, n.val);
				}
				min = newmin;
			}
			
			return newhead;
		}
	}
}

