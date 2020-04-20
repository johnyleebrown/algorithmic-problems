package hashtable;

/**
 * 575
 */
public class DistributeCandies
{
	class Solution 
	{
		public int distributeCandies(int[] candies) 
		{
			boolean[] types = new boolean[200001];
			int res = 0;
			for (int i = 0; i < candies.length; i++)
			{
				if(!types[candies[i]+100000])
				{
					types[candies[i]+100000] = true;
					res++;
					if(res == candies.length/2) return res;
				}
			}

			return res;
		}
	}
}

