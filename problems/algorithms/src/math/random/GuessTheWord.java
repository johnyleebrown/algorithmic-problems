package math.random;

import java.util.*;

/**
 * 843
 *
 * ======
 *
 * Task.
 *
 * This problem is an interactive problem new to the LeetCode platform.
 *
 * We are given a word list of unique words, each word is 6 letters long, and
 * one word in this list is chosen as secret.
 *
 * You may call master.guess(word) to guess a word.  The guessed word should
 * have type string and must be from the original list with 6 lowercase
 * letters.
 *
 * This function returns an integer type, representing the number of exact
 * matches (value and position) of your guess to the secret word.  Also, if your
 * guess is not in the given wordlist, it will return -1 instead.
 *
 * For each test case, you have 10 guesses to guess the word. At the end of any
 * number of calls, if you have made 10 or less calls to master.guess and at
 * least one of these guesses was the secret, you pass the testcase.
 *
 * Besides the example test case below, there will be 5 additional test cases,
 * each with 100 words in the word list.  The letters of each word in those
 * testcases were chosen independently at random from 'a' to 'z', such that
 * every word in the given word lists is unique.
 *
 * ======
 *
 * Source: Leetcode
 */
public class GuessTheWord
{
	/**
	 * The idea is to pick the word at random, the find words that are similar
	 * to it, and repeat the process. For better chances we pick random 3 times,
	 * it gives us an opportunity to better choose possible matches. We could
	 * use seen set to pick different indexes, but it turns out, we might stuck
	 * in the loop if we have not enough variants to pick from.
	 */
	static class Solution
	{
		private Random r = new Random();

		public void findSecretWord(String[] w, Master m)
		{
			List<String> good = new ArrayList<>(Arrays.asList(w));
			for (int i = 0; i < 4; i++)
			{
				int currentLen = good.size();
				String wordPick1 = good.get(r.nextInt(currentLen));
				int ans1 = m.guess(wordPick1);
				if (ans1 == 6)
					return;
				String wordPick2 = good.get(r.nextInt(currentLen));
				int ans2 = m.guess(wordPick2);
				if (ans2 == 6)
					return;
				String wordPick3 = good.get(r.nextInt(currentLen));
				int ans3 = m.guess(wordPick3);
				if (ans3 == 6)
					return;

				List<String> newl = new ArrayList<>();
				for (String possibleGuess : good)
					if (countSim(wordPick1, possibleGuess) == ans1
							&& countSim(wordPick2, possibleGuess) == ans2
							&& countSim(wordPick3, possibleGuess) == ans3)
						newl.add(possibleGuess);

				if (!newl.isEmpty())
					good = newl;
			}
		}

		private int countSim(String pick, String w)
		{
			int c = 0;
			for (int k = 0; k < 6; k++)
				if (pick.charAt(k) == w.charAt(k))
					c++;
			return c;
		}

		private interface Master
		{
			int guess(String w);
		}
	}
}
