package math.detectRecurrence;

import util.utils.reader.InputReader;

import java.io.PrintWriter;

/**
 * 624Div3A
 *
 * ======
 *
 * Task.
 *
 * You are given two positive integers 𝑎 and 𝑏. In one move, you can change 𝑎
 * in the following way: Choose any positive odd integer 𝑥 (𝑥>0) and replace
 * 𝑎 with 𝑎+𝑥 ; choose any positive even integer 𝑦 (𝑦>0) and replace 𝑎
 * with 𝑎−𝑦. You can perform as many such operations as you want. You can
 * choose the same numbers 𝑥 and 𝑦 in different moves. Your task is to find
 * the minimum number of moves required to obtain 𝑏 from 𝑎. It is guaranteed
 * that you can always obtain 𝑏 from 𝑎. You have to answer 𝑡 independent test
 * cases.
 *
 * ======
 *
 * Source: Codeforces
 */
public class AddOddOrSubtractEven
{
	/**
	 * SF.
	 */
	private static class Solution
	{
		private static void s(InputReader in, PrintWriter out)
		{
			int a = in.nextInt();
			int b = in.nextInt();
			if (a == b)
				out.println(0);
			else if (((a % 2 == 0 && b % 2 == 0) || (a % 2 != 0 && b % 2 != 0)) && (a > b))
				out.println(1);
			else if (a < b && ((a % 2 != 0 && b % 2 == 0) || (a % 2 == 0 && b % 2 != 0)))
				out.println(1);
			else
				out.println(2);
		}
	}
}
