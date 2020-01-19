package twoPointers.slidingWindow.max;

import util.codeforces.Main;

import java.io.PrintWriter;

/**
 * Task C 256854
 *
 * ======
 *
 * Task.
 *
 * You are given an array 𝑑1,𝑑2,…,𝑑𝑛 consisting of 𝑛 integer numbers.
 *
 * Your task is to split this array into three parts (some of which may be empty) in such a way that each element of the
 * array belongs to exactly one of the three parts, and each of the parts forms a consecutive contiguous subsegment
 * (possibly, empty) of the original array.
 *
 * Let the sum of elements of the first part be 𝑠𝑢𝑚1, the sum of elements of the second part be 𝑠𝑢𝑚2 and the sum
 * of elements of the third part be 𝑠𝑢𝑚3. Among all possible ways to split the array you have to choose a way such
 * that 𝑠𝑢𝑚1=𝑠𝑢𝑚3 and 𝑠𝑢𝑚1 is maximum possible.
 *
 * More formally, if the first part of the array contains 𝑎 elements, the second part of the array contains 𝑏 elements
 * and the third part contains 𝑐 elements, then: 𝑠𝑢𝑚1=∑1≤𝑖≤𝑎𝑑𝑖, 𝑠𝑢𝑚2=∑𝑎+1≤𝑖≤𝑎+𝑏𝑑𝑖,
 * 𝑠𝑢𝑚3=∑𝑎+𝑏+1≤𝑖≤𝑎+𝑏+𝑐𝑑𝑖. The sum of an empty array is 0.
 *
 * Your task is to find a way to split the array such that 𝑠𝑢𝑚1=𝑠𝑢𝑚3 and 𝑠𝑢𝑚1 is maximum possible.
 *
 * ======
 *
 * Source: Codeforces
 */
public class ThreePartsOfTheArray
{
	static class Solution
	{
		public void solve(int testNumber, Main.InputReader in, PrintWriter out)
		{
			int n = in.nextInt();
			Long[] nums = new Long[n];
			for (int i = 0; i < n; i++)
			{
				nums[i] = in.nextLong();
			}

			// sum of the elements of the 1st part of the array
			long sum1 = 0L;
			// sum of the elements of the 2nd part of the array
			long sum2 = 0L;
			long result = 0L;
			// moving r from the right side of the array
			int r = n;

			for (int l = 0; l < n; l++)
			{
				// use left pointer
				sum1 += nums[l];

				// while bad condition move other pointer
				while (sum2 < sum1)
				{
					r--;

					// use right pointer
					sum2 += nums[r];
				}

				if (sum1 == sum2 && r > l)
				{
					result = Math.max(result, sum1);
				}
			}

			out.println(result);
		}
	}
}