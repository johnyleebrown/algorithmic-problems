// 163
class Solution 
{
    public List<String> findMissingRanges(int[] nums, int lower, int upper) 
    {
		String s = "->";
		int n = nums.length;
		List<String> li = new ArrayList<>();
		int l = lower, r = 0;
		for (int i = 0; i <= n; i++)
		{
			if (l == upper)
			{
				break;
			}

			if (i != n)
			{
				r = nums[i];
			}
			else
			{
				r = upper;
			}

			if (((r - 1) == (l + 1)))
			{
				int x = r == upper ? upper : r - 1;
				li.add(String.valueOf(x));
			}
			else if (r - l > 1)
			{
				int y = l == lower ? lower : l + 1;
                int x = r == upper ? upper : r - 1;
				li.add((y) + s + (x));
			}

			if (i != n)
			{
				l = nums[i];
			}
		}
		return li;
    }
}
/*

[99]
0
100

["1->98"]

["0->98","100"]

*/
