package math.numberTheory;

import util.tester.Tester;

import java.util.Arrays;

/**
 * 1363
 *
 * ======
 *
 * Task.
 *
 * Given an integer array of digits, return the largest multiple of three that
 * can be formed by concatenating some of the given digits in any order.
 *
 * Since the answer may not fit in an integer data type, return the answer as a
 * string.
 *
 * If there is no answer return an empty string.
 *
 * ======
 *
 * Source: Leetcode
 */
public class LargestMultipleOfThree
{
	/**
	 * =====
	 *
	 * Description.
	 *
	 * If the number's sum of digits is divisible by 3, then the number is too.
	 * So we count the sum, if the sum is divisible, we return just a sorted
	 * variant. If not, we check the remainders, there could be three of them:
	 * 0,1,2. 0 we discussed. If 1, we need to remove the number that has a
	 * rem=1 or 2 numbers that have rem=2. If 2, we need to remove the number
	 * that has a rem=2 or 2 numbers that have rem=1.
	 *
	 *
	 * =====
	 *
	 * Reference.
	 *
	 * https://zenomath.org/wp-content/uploads/2016/04/Patterns-in-Multiples-of-3-and-9.pdf.
	 */
	private static class Solution
	{
		private int MAX_NUMBER_OF_DIGITS = 10;
		private int n;

		public String largestMultipleOfThree(int[] a)
		{
			n = a.length;
			return getLargestMultipleOf3(a);
		}

		private String getLargestMultipleOf3(int[] a)
		{
			int sum = accumulate(a);
			Arrays.sort(a);
			int remainder = sum % 3;

			if (remainder == 0)
			{
				return getResultExcludingIndexes(a, -1, -1);
			}
			else if (remainder == 1)
			{
				return actionOnRemainder1(a);
			}
			else
			{
				return actionOnRemainder2(a);
			}
		}

		/**
		 * If remainder is '1', we have to delete either one element with
		 * remainder '1' or two elements with remainder '2'. Example: 1 1 2 3, 5
		 * 5 3
		 */
		private String actionOnRemainder1(int[] a)
		{
			int[] indexesToRemove = new int[]{-1, -1};

			// 1st case
			for (int i = 0; i < n; i++)
				if (a[i] % 3 == 1)
					return getResultExcludingIndexes(a, i, -1);

			// 2nd case
			for (int i = 0; i < n; i++)
				if (a[i] % 3 == 2)
					if (indexesToRemove[0] == -1)
						indexesToRemove[0] = i;
					else if (indexesToRemove[1] == -1)
						indexesToRemove[1] = i;

			if (indexesToRemove[0] != -1 && indexesToRemove[1] != -1)
				return getResultExcludingIndexes(a, indexesToRemove[0], indexesToRemove[1]);
			else
				return "";
		}

		/**
		 * If remainder is '2', we have to delete either one element of
		 * remainder '2' or two elements of remainder '1'.
		 */
		private String actionOnRemainder2(int[] a)
		{
			int[] indexesToRemove = new int[]{-1, -1};

			for (int i = 0; i < n; i++)
				if (a[i] % 3 == 2)
					return getResultExcludingIndexes(a, i, -1);

			for (int i = 0; i < n; i++)
				if (a[i] % 3 == 1)
					if (indexesToRemove[0] == -1)
						indexesToRemove[0] = i;
					else if (indexesToRemove[1] == -1)
						indexesToRemove[1] = i;

			if (indexesToRemove[0] != -1 && indexesToRemove[1] != -1)
				return getResultExcludingIndexes(a, indexesToRemove[0], indexesToRemove[1]);
			else
				return "";
		}

		/**
		 * Remove elements from arr[]. Index which is -1 is nonexistent.
		 */
		private String getResultExcludingIndexes(int[] a, int ignoreIndex1, int ignoreIndex2)
		{
			StringBuilder sb = new StringBuilder();

			for (int i = n - 1; i >= 0; i--)
				if (i != ignoreIndex1 && i != ignoreIndex2)
					sb.append(a[i]);

			if (sb.length() > 0 && sb.charAt(0) == '0')
                return "a0";

			return sb.toString();
		}

		private int accumulate(int[] a)
		{
			int sum = 0;
			for (int value : a)
				sum += value;
			return sum;
		}
	}

	public static void main(String[] args)
	{
        new Tester(new Solution())
                .add(new int[]{8, 1, 9}).expect("981")
                .add(new int[]{8, 6, 7, 1, 0}).expect("8760")
                .add(new int[]{1}).expect("")
                .add(new int[]{0, 0, 0, 0, 0}).expect("a0")
				.run();
	}
}
