package math.random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 843
 */
public class GuessTheWord {

	/**
	 * The idea is to pick the word at random, the find words that are similar
	 * to it, and repeat the process. For better chances we pick random 3 times,
	 * it gives us an opportunity to better choose possible matches. We could
	 * use seen set to pick different indexes, but it turns out, we might stuck
	 * in the loop if we have not enough variants to pick from.
	 */
	static class Solution {

		public void findSecretWord(String[] w, Master m) {
			Random r = new Random();
			List<String> good = new ArrayList<>(Arrays.asList(w));
			for (int i = 0; i < 4; i++) {
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
				for (String possibleGuess : good) {
					if (countSim(wordPick1, possibleGuess) == ans1
							&& countSim(wordPick2, possibleGuess) == ans2
							&& countSim(wordPick3, possibleGuess) == ans3)
						newl.add(possibleGuess);
				}

				if (!newl.isEmpty())
					good = newl;
			}
		}

		private int countSim(String pick, String w) {
			int c = 0;
			for (int k = 0; k < 6; k++) {
				if (pick.charAt(k) == w.charAt(k))
					c++;
			}
			return c;
		}

		private interface Master {

			int guess(String w);
		}
	}
}
