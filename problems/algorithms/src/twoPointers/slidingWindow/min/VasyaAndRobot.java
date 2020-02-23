package twoPointers.slidingWindow.min;

import util.ds.InputReader;

import java.io.PrintWriter;

/**
 * 256854D
 *
 * ======
 *
 * Task.
 *
 * Vasya has got a robot which is situated on an infinite Cartesian plane, initially in the cell (0,0). Vasya also has
 * got a sequence of 𝑛 operations. Vasya wants to modify this sequence so after performing it the robot will end up in
 * (𝑥,𝑦).
 *
 * Vasya wants to change the sequence so the length of changed subsegment is minimum possible. This length can be
 * calculated as follows: 𝑚𝑎𝑥𝐼𝐷−𝑚𝑖𝑛𝐼𝐷+1, where 𝑚𝑎𝑥𝐼𝐷 is the maximum index of a changed operation, and
 * 𝑚𝑖𝑛𝐼𝐷 is the minimum index of a changed operation. For example, if Vasya changes RRRRRRR to RLRRLRL, then the
 * operations with indices 2, 5 and 7 are changed, so the length of changed subsegment is 7−2+1=6. Another example: if
 * Vasya changes DDDD to DDRD, then the length of changed subsegment is 1.
 *
 * If there are no changes, then the length of changed subsegment is 0. Changing an operation means replacing it with
 * some operation (possibly the same); Vasya can't insert new operations into the sequence or remove them.
 *
 * Help Vasya! Tell him the minimum length of subsegment that he needs to change so that the robot will go from (0,0) to
 * (𝑥,𝑦), or tell him that it's impossible.
 *
 * ======
 *
 * Source: Codeforces
 */
public class VasyaAndRobot
{
	public void solve(int testNumber, InputReader in, PrintWriter out)
	{
		int n = in.nextInt();
		String moves = in.next();
		int x = in.nextInt();
		int y = in.nextInt();

		long result = n;
		int[] dx = new int[n];
		int[] dy = new int[n];
		for (int i = 0; i < n; i++)
		{
			switch (moves.charAt(i))
			{
				case 'U':
					dy[i] = 1;
					break;
				case 'D':
					dy[i] = -1;
					break;
				case 'R':
					dx[i] = 1;
					break;
				case 'L':
					dx[i] = -1;
					break;
			}
		}

		int curX = 0;
		int curY = 0;
		for (int i = 0; i < n; i++)
		{
			curX += dx[i];
			curY += dy[i];
		}

		if ((Math.abs(x) + Math.abs(y)) % 2 != n % 2 || Math.abs(x) + Math.abs(y) > n)
		{
			out.println(-1);
		}
		else if (curX == x && curY == y)
		{
			out.println(0);
		}
		else
		{
			int l = 0;
			for (int r = 0; r < n; r++)
			{
				curX -= dx[r];
				curY -= dy[r];

				while (Math.abs(curX - x) + Math.abs(curY - y) <= r - l + 1)
				{
					curX += dx[l];
					curY += dy[l++];
				}

				if (l > 0)
				{
					result = Math.min(result, r - l + 2);
				}
			}
			out.println(result);
		}
	}
}
