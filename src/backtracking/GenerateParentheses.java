package medium.backtracking;

import java.util.List;
import java.util.ArrayList;

// 22
public class GenerateParentheses
{
	private static class Solution
	{
		private List<String> answerList = new ArrayList<>();

		public List<String> generateParenthesis(int n)
		{
			if (n < 1) return answerList;
			generate(n, 0, 0, 0, "");
			return answerList;
		}

		private void generate(int n, int k, int left, int right, String comb)
		{
			if (left == right && k == n*2) answerList.add(comb);
			else
			{
				// we can add a right par when the n of left is more then right
				if (right < left) generate(n, k + 1, left, right + 1, comb + ')');
				// we can keep adding left untill the num of them is less then n/2
				if (left <= n) generate(n, k + 1, left + 1, right, comb + '(');
			}
		}

		public List<String> getAns()
		{
			return answerList;
		}
	}

	public static void main(String[] args)
	{
		Solution s = new Solution();
		s.generateParenthesis(3);
		System.out.println(s.getAns());
	}

}

