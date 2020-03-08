package recursion;

/**
 * 779
 */
public class KthSymbolInGrammar
{
	class Solution
	{
		/**
		 * Using recursion go to the first node to find out what it is Every
		 * time knowing a parent and current K we can calculate current value
		 * THis value will be returned every time to guess the next value
		 */
		public int kthGrammar(int N, int K)
		{
			if (N == 1) return 0;
			// K + 1 because we want to be placed at the right location
			// since 1/2 is 0, but we need it to be 1
			int parent = kthGrammar(N - 1, (K + 1) / 2);
			// this part is from the task
			return calculateCurrent(parent, K);
		}

		private int calculateCurrent(int parent, int K)
		{
			if ((parent == 0 && K % 2 == 1) || (parent == 1 && K % 2 == 0))
				return 0;
			// else if ((parent == 1 && K % 2 == 1) || (parent == 0 && K % 2 == 0)) return 1;
			// equivalent of ktGrammar() == K % 2 ? 1 : 0;
			return 1;
		}

	}

	class Solution2
	{
		public int kthGrammar(int N, int K)
		{
			// base case
			if (K == 1) return 0;
			// there are basically 4 cases, this is reduced version
			if (K % 2 == kthGrammar(N - 1, (K + 1) / 2)) return 1;
			else return 0;
		}
	}
}