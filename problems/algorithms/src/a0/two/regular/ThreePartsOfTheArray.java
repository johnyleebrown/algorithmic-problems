package a0.two.regular;

import util.ds.InputReader;

import java.io.PrintWriter;

/**
 * 256854C
 *
 * ======
 *
 * Task.
 *
 * You are given an array 𝑑1,𝑑2,…,𝑑𝑛 consisting of 𝑛 integer numbers.
 *
 * Your task is to split this array into three parts (some of which may be
 * empty) in such a way that each element of the array belongs to exactly one of
 * the three parts, and each of the parts forms a consecutive contiguous
 * subsegment (possibly, empty) of the original array.
 *
 * Let the sum of elements of the first part be 𝑠𝑢𝑚1, the sum of elements of
 * the second part be 𝑠𝑢𝑚2 and the sum of elements of the third part be
 * 𝑠𝑢𝑚3. Among all possible ways to split the array you have to choose a way
 * such that 𝑠𝑢𝑚1=𝑠𝑢𝑚3 and 𝑠𝑢𝑚1 is maximum possible.
 *
 * More formally, if the first part of the array contains 𝑎 elements, the
 * second part of the array contains 𝑏 elements and the third part contains 𝑐
 * elements, then: 𝑠𝑢𝑚1=∑1≤𝑖≤𝑎𝑑𝑖, 𝑠𝑢𝑚2=∑𝑎+1≤𝑖≤𝑎+𝑏𝑑𝑖,
 * 𝑠𝑢𝑚3=∑𝑎+𝑏+1≤𝑖≤𝑎+𝑏+𝑐𝑑𝑖. The sum of an empty array is 0.
 *
 * Your task is to find a way to split the array such that 𝑠𝑢𝑚1=𝑠𝑢𝑚3 and
 * 𝑠𝑢𝑚1 is maximum possible.
 *
 * ======
 *
 * Source: Codeforces
 *
 * Link: https://codeforces.com/group/pgkaqF4igo/contest/256854/problem/C
 */
public class ThreePartsOfTheArray
{
	static class Solution
	{
		public void solve(int testNumber, InputReader in, PrintWriter out)
		{
			int n = in.nextInt();
			Long[] a = in.nextLongAr(n);
			int l = 0;
			int r = n - 1;
			Long res = 0L;
			Long sl=a[l];//left sum
			Long sr=a[r];//right sum
			while (l < r)
			{
				if (sl<sr)
				{
					l++;
					sl+=a[l];
				}
				else if (sl>sr)
				{
					r--;
					sr+=a[r];
				}
				else
				{
					res = Math.max(res, sl);
					l++;
					r--;
					sl+=a[l];
					sr+=a[r];
				}
			}
			out.println(res);
		}
	}
}