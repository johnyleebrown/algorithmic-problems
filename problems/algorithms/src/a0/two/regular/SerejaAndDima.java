package a0.two.regular;

import util.ds.InputReader;

import java.io.PrintWriter;

/**
 * 381 A
 *
 * ======
 *
 * Task.
 *
 * Sereja and Dima play a game. The rules of the game are very simple. The players have n cards in a row. Each card
 * contains a number, all numbers on the cards are distinct. The players take turns, Sereja moves first. During his turn
 * a player can take one card: either the leftmost card in a row, or the rightmost one. The game ends when there is no
 * more cards. The player who has the maximum sum of numbers on his cards by the end of the game, wins.
 *
 * Sereja and Dima are being greedy. Each of them chooses the card with the larger number during his move. Inna is a
 * friend of Sereja and Dima. She knows which strategy the guys are using, so she wants to determine the final score,
 * given the initial state of the game. Help her.
 *
 * ======
 *
 * Source: Codeforces
 */
public class SerejaAndDima
{
	public void solve(int testNumber, InputReader in, PrintWriter out)
	{
		int n = in.nextInt();
		Long[] cards = new Long[n];
		for (int i = 0; i < n; i++)
		{
			cards[i] = in.nextLong();
		}

		int first = 0;
		int second = 0;
		int l = 0;
		int r = n - 1;
		boolean firstPlayerTurn = true;

		while (l <= r)
		{
			if (cards[l] < cards[r])
			{
				if (firstPlayerTurn)
				{
					first += cards[r];
					firstPlayerTurn = false;
				}
				else
				{
					second += cards[r];
					firstPlayerTurn = true;
				}

				r--;
			}
			else
			{
				if (firstPlayerTurn)
				{
					first += cards[l];
					firstPlayerTurn = false;
				}
				else
				{
					second += cards[l];
					firstPlayerTurn = true;
				}

				l++;
			}
		}

		out.println(first + " " + second);
	}
}
