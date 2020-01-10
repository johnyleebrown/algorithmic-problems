/*
 * 974
 * Yandex, Amazon
 */
public class SubarraySumsDivisibleByK
{
	/*
	 * O(n)
	 * 
	 */
	class Solution 
	{
		public int subarraysDivByK(int[] A, int K) 
		{
			int[] sum = new int[K];
			sum[0] = 1;
			int pre = 0, count = 0;
			for (int i: A)
			{
				pre += i;
				int rem = pre % K;
				if (rem < 0) rem += K;
				count += sum[rem];
				sum[rem]++; 
			}
			return count;
		}
	}
}

