package dp.subsequences.commonSubsequences.count;

/**
 * 115
 *
 * ======
 *
 * Task.
 *
 * Given a string S and a string T, count the number of distinct subsequences of S which equals T.
 *
 * A subsequence of a string is a new string which is formed from the original string by deleting
 * some (can be none) of the characters without disturbing the relative positions of the remaining
 * characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 *
 * It's guaranteed the answer fits on a 32-bit signed integer.
 *
 * ======
 *
 * Source: Leetcode
 */
public class DistinctSubsequences {
	/**
	 * Рассуждения:
	 * state:
	 * dp[i][j] - кол-во subsequences s[0:j] кот можно сделать из t[0:i]
	 *
	 * начальные состояния:
	 * - кол-во уникальных подстрок s которые можно сделать с пустотой ровно 1,
	 * поэтому первый ряд заполняем 1
	 * - если s пустая то никакая строка не будет ее подстрокой,
	 * поэтому первый столбец заполняем 0
	 *
	 * рекуррентное соотношение:
	 * проще дойти до этого сначала продумав рекурсивное решение, в рекурсии состояние f(s, j, t, i)
	 * - если у нас символы i и j равны
	 * будет получено из f(s,j-1,t,i-1) - символы совпали и мы хотим взять следующую пару
	 * и из f(s,j-1,t,i) - символы совпали, но может в s найдется еще одно совпадение нашему
	 * символу i
	 * в итоге f(s,j-1,t,i-1)+f(s,j-1,t,i) потому что может быть и то и другое
	 * - если не равны
	 * мы берем f(s,j-1,t,i) потому что нашему i из t мы не нашли совпадение с j, поэтому мы
	 * будем продолжать искать в s и поэтому смещаем индекс
	 *
	 * ключевой момент:
	 * если символы совпали, взять еще и состояние f(s,j-1,t,i) потому что нашему символу i в t
	 * мы может еще найдем совпадение если сдвинем j в s.
	 */
	public static class Solution {
		public int numDistinct(String s, String t) {
			int tn = t.length(), sn = s.length();
			int[][] dp = new int[tn + 1][sn + 1];

			// base
			for (int i = 0; i < sn; i++) {
				dp[0][i] = 1;
			}

			for (int i = 1; i <= tn; i++) {
				for (int j = 1; j <= sn; j++) {
					if (t.charAt(i - 1) == s.charAt(j - 1)) {
						dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
					} else {
						dp[i][j] = dp[i][j - 1];
					}
				}
			}

			return dp[tn][sn];
		}
	}
}