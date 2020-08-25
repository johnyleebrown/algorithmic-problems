package math.count;

import util.tester.Tester;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 1360
 *
 * ======
 *
 * Task.
 *
 * Write a program to count the number of days between two dates.
 *
 * The two dates are given as strings, their format is YYYY-MM-DD as shown in
 * the examples.
 *
 * ======
 *
 * Source: Leetcode
 */
public class NumberOfDaysBetweenTwoDates
{
	/**
	 * Straightforward.
	 */
	private static class Solution
	{
		public int daysBetweenDates(String date1, String date2)
		{
			int d1y = Integer.parseInt(date1.substring(0, 4));
			int d1m = Integer.parseInt(date1.substring(5, 7));
			int d1d = Integer.parseInt(date1.substring(8, 10));
			int d2y = Integer.parseInt(date2.substring(0, 4));
			int d2m = Integer.parseInt(date2.substring(5, 7));
			int d2d = Integer.parseInt(date2.substring(8, 10));
			return Math.abs(countDays(d1y, d1m, d1d)-countDays(d2y, d2m, d2d));
		}

		private int countDays(int y, int m, int d)
		{
			int sum = 0;
			for (int i = 1971; i < y; i++)
			{
				sum += 365;
				if (isLeapYear(i))
					sum++;
			}
			int[] monthDays = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
			for (int i = 0; i < m - 1; i++)
			{
				sum += monthDays[i];
				if (i == 1 && isLeapYear(i))
					sum++;
			}
			sum += d;
			return sum;
		}

		private boolean isLeapYear(int y)
		{
			return y % 400 == 0 || y % 4 == 0 || y % 100 != 0;
		}
	}

	private static class Solution2
	{
		public int daysBetweenDates(String date1, String date2)
		{
			Date firstDate = parse(date1);
			Date secondDate = parse(date2);
			long diff = Math.abs(secondDate.getTime() - firstDate.getTime());
			return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		}

		private Date parse(String d)
		{
			try
			{
				return new SimpleDateFormat("yyyy-MM-dd").parse(d);
			}
			catch (ParseException e)
			{
				throw new RuntimeException();
			}
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add("2019-06-29", "2019-06-30").expect(1)
				.add("2020-01-15", "2019-12-31").expect(15)
				.run();
	}
}
