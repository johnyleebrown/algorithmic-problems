package dp._6_Trees;

import util.tester.Tester;

/**
 * 465Div2E
 *
 * ======
 *
 * Task.
 *
 * Ancient Egyptians are known to have understood difficult concepts in
 * mathematics. The ancient Egyptian mathematician Ahmes liked to write a kind
 * of arithmetic expressions on papyrus paper which he called as Ahmes
 * arithmetic expression.
 *
 * An Ahmes arithmetic expression can be defined as:
 *
 * "d" is an Ahmes arithmetic expression, where d is a one-digit positive
 * integer; "(E1 op E2)" is an Ahmes arithmetic expression, where E1 and E2 are
 * valid Ahmes arithmetic expressions (without spaces) and op is either plus
 * ( + ) or minus ( - ).
 *
 * For example 5, (1-1) and ((1+(2-3))-5) are valid Ahmes arithmetic
 * expressions.
 *
 * On his trip to Egypt, Fafa found a piece of papyrus paper having one of these
 * Ahmes arithmetic expressions written on it. Being very ancient, the papyrus
 * piece was very worn out. As a result, all the operators were erased, keeping
 * only the numbers and the brackets. Since Fafa loves mathematics, he decided
 * to challenge himself with the following task:
 *
 * Given the number of plus and minus operators in the original expression, find
 * out the maximum possible value for the expression on the papyrus paper after
 * putting the plus and minus operators in the place of the original erased
 * operators.
 *
 * ======
 *
 * Source: Codeforces
 */
public class FafaAndAncientMathematics
{
	/**
	 *
	 */
	public static class Solution
	{
		public int solve()
		{
			return -1;
		}

		public Solution()
		{
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add().expect()
				.run();
	}
}
