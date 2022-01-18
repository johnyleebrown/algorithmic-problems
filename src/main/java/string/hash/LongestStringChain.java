package string.hash;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 1048
 */
public class LongestStringChain {

	/**
	 * We use formula to concat substrings: substringHash =
	 * h(left)*R^(rightLen)+h(right).
	 */
	public static class Solution3 {

		private final long R = 256;
		private long[] pow;
		private long Q;

		public int longestStrChain(String[] words) {
			Arrays.sort(words, (a, b) -> a.length() - b.length());
			Map<String, Long> hashes = new HashMap<>();
			Map<Long, Integer> dp = new HashMap<>();
			preprocess(words, hashes, dp);

			int ans = 0;
			for (String word : words) {
				long currentWordHash = hashes.get(word);
				long left = 0;
				long right = currentWordHash;

				for (int i = 0; i < word.length(); i++) {
					if (i > 0) left = addLast(left, word.charAt(i - 1));
					right = removeFirst(right, word.length() - i, word.charAt(i));
					long sum = sum(left, right, word.length() - i - 1);

					if (dp.containsKey(sum)) {
						dp.put(hashes.get(word), Math.max(dp.get(currentWordHash), dp.get(sum) + 1));
					}
				}
				ans = Math.max(ans, dp.get(currentWordHash));
			}

			return ans;
		}

		private void preprocess(String[] words, Map<String, Long> hashes,
				Map<Long, Integer> dp) {
			pow = new long[words[words.length - 1].length() + 1];
			Q = getRandPrime();
			pow[0] = 1;
			for (int i = 1; i < pow.length; i++) {
				pow[i] = (pow[i - 1] * R) % Q;
			}

			for (String word : words) {
				long h = hash(word);
				dp.put(h, 1);
				hashes.put(word, h);
			}
		}

		private long hash(String s) {
			return hash(s, 0, s.length() - 1);
		}

		private long hash(String s, int l, int r) {
			long h = 0;
			for (int i = l; i <= r; i++) {
				h = (h * R + s.charAt(i)) % Q;
			}
			return h;
		}

		private long sum(long left, long right, int len) {
			return (left * pow[len] + right) % Q;
		}

		private long addLast(long h, char c) {
			return (h * R + c) % Q;
		}

		private long removeFirst(long h, int len, char c) {
			return (h + Q - (pow[len - 1] * c) % Q) % Q;
		}

		private long getRandPrime() {
			BigInteger prime = BigInteger.probablePrime(31, new Random());
			return prime.longValue();
		}
	}

}