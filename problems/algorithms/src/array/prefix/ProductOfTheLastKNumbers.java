package array.prefix;

import java.util.ArrayList;
import java.util.List;

/**
 * 1352
 *
 * ======
 *
 * Task.
 *
 * Implement the class ProductOfNumbers that supports two methods:
 *
 * 1. add(int num)
 *
 * Adds the number num to the back of the current list of numbers.
 *
 * 2. getProduct(int k)
 *
 * Returns the product of the last k numbers in the current list. You can assume
 * that always the current list has at least k numbers.
 *
 * At any time, the product of any contiguous sequence of numbers will fit into
 * a single 32-bit integer without overflowing.
 *
 * ======
 *
 * Tests.
 *
 * ["ProductOfNumbers","add","add","add","add","add","getProduct","getProduct","getProduct","add","getProduct"]
 * [[],[3],[0],[2],[5],[4],[2],[3],[4],[8],[2]]
 *
 * ======
 *
 * Source: Leetcode
 */
public class ProductOfTheLastKNumbers
{
	/**
	 * Clear when encounter a zero, because every product of subarray involving
	 * zero is zero.
	 */
	private class Solution
	{
		class ProductOfNumbers
		{
			List<Integer> pros = new ArrayList<>();

			public ProductOfNumbers()
			{
			}

			public void add(int num)
			{
				if (num == 0)
				{
					pros.clear();
				}
				else
				{
					if (pros.size() > 0)
						pros.add(pros.get(pros.size() - 1) * num);
					else
						pros.add(num);
				}
			}

			public int getProduct(int k)
			{
				int s = pros.size();
				if (k > s)
					return 0;
				else
					return s - k - 1 < 0 ? pros.get(s - 1) : pros.get(s - 1) / pros.get(s - k - 1);
			}
		}
	}
}