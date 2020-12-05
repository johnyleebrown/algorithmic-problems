package regular.array;

/**
 * 243
 *
 * ======
 *
 * Given a list of words and two words word1 and word2, return the shortest distance between these
 * two words in the list.
 *
 * ======
 *
 * https://leetcode.com/problems/shortest-word-distance/
 */
public class ShortestWordDistance {
	/**
	 * if meet same word = clear dist ..w1..w1..
	 * if meet other one = record dist ..w1..w2..w1
	 */
	public static class Solution {
		public int shortestDistance(String[] words, String word1, String word2) {
			int ans = Integer.MAX_VALUE;
			String last = null;
			int local = 0;
			for (String w : words) {
				if (word1.equals(w) || word2.equals(w)) {
					if (last != null && !w.equals(last)) {
						ans = Math.min(ans, local);
					}
					local = 0;
					last = w;
				}
				local++;
			}
			return ans;
		}
	}

	public static class InitialSolution {
		public int shortestDistance(String[] words, String word1, String word2) {
			int ans = Integer.MAX_VALUE;
			String last = null;
			int local = 0;
			for (String w : words) {
				if (word1.equals(w)) {
					if (last == null) {
						local = 0;
					} else {
						if (w.equals(last)) {
							local = 0;
						} else {
							ans = Math.min(ans, local);
							local = 0;
						}
					}
					last = w;
				} else if (word2.equals(w)) {
					if (last == null) {
						local = 0;
					} else {
						if (w.equals(last)) {
							local = 0;
						} else {
							ans = Math.min(ans, local);
							local = 0;
						}
					}
					last = w;
				}
				local++;
			}
			return ans;
		}
	}
}