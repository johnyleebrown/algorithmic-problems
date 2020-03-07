package unionFind;

/**
 * 1303_F
 *
 * ======
 *
 * Task.
 *
 * You are given a matrix 𝑛×𝑚, initially filled with zeroes. We define 𝑎𝑖,𝑗
 * as the element in the 𝑖-th row and the 𝑗-th column of the matrix.
 *
 * Two cells of the matrix are connected if they share a side, and the elements
 * in these cells are equal. Two cells of the matrix belong to the same
 * connected component if there exists a sequence 𝑠1 , 𝑠2, ..., 𝑠𝑘 such that
 * 𝑠1 is the first cell, 𝑠𝑘 is the second cell, and for every 𝑖∈[1,𝑘−1],
 * 𝑠𝑖 and 𝑠𝑖+1 are connected.
 *
 * You are given 𝑞 queries of the form 𝑥𝑖 𝑦𝑖 𝑐𝑖 (𝑖∈[1,𝑞]). For every
 * such query, you have to do the following: replace the element 𝑎𝑥,𝑦 with 𝑐
 * count the number of connected components in the matrix.
 *
 * There is one additional constraint: for every 𝑖∈[1,𝑞−1] , 𝑐𝑖≤𝑐𝑖+1.
 *
 * ======
 *
 * Source: Codeforces
 */
public class NumberOfComponents
{
	/**
	 *
	 */
	private class Solution
	{
		public int solve()
		{
			return -1;
		}
	}
}