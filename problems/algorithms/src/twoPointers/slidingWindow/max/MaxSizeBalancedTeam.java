package twoPointers.slidingWindow.max;

import util.ds.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

/**
 * 256854B
 *
 * ======
 *
 * Task.
 *
 * You are a coach at your local university. There are 𝑛 students under your
 * supervision, the programming skill of the 𝑖-th student is 𝑎𝑖. You have to
 * create a team for a new programming competition. As you know, the more
 * students some team has the more probable its victory is! So you have to
 * create a team with the maximum number of students. But you also know that a
 * team should be balanced. It means that the programming skill of each pair of
 * students in a created team should differ by no more than 5. Your task is to
 * report the maximum possible number of students in a balanced team.
 *
 * ======
 *
 * Source: Codeforces
 */
public class MaxSizeBalancedTeam
{
	static class Solution
	{
		public void solve(int testNumber, InputReader in, PrintWriter out)
		{
			int n = in.nextInt(); // number of students
			Long[] students = new Long[n]; // student powers
			for (int i = 0; i < n; i++)
			{
				students[i] = in.nextLong();
			}

			Arrays.sort(students);

			int result = 0;
			int l = 0;

			for (int r = 0; r < n; r++)
			{
				while (students[r] - students[l] > 5)
				{
					l++;
				}

				result = Math.max(result, r - l + 1);
			}

			out.println(result);
		}
	}
}
