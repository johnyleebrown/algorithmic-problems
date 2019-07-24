package medium.backtracking;

/*
 * 22
 *
 * generate well-formed parentheses
 */
public class GenerateParentheses
{
	class Solution {
		
		private final static char OP= '(', CP=')';
		private final static char[] ps = new char[]{OP, CP};

		public List<String> generateParenthesis(int n) {
			List<String> answerList = new LinkedList<>();
			if (n < 1) return answerList;
			generateCombinations(n, n, answerList, "", n * 2);
			return answerList;			
		}

		private void generateCombinations(int leftCount, int rightCount, 
				List<String> list, String combination, int n)
		{
			if (n == 0) list.add(combination);
			else 
			{
				if (rightCount > leftCount)
				{
					generateCombinations(leftCount, rightCount - 1, 
							list, combination + CP, n - 1);
				}
				if (leftCount != 0)
				{
					generateCombinations(leftCount - 1, rightCount, 
							list, combination + OP, n - 1);
				}
			}
		}
	}
}

