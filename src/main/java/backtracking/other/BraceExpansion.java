package backtracking.other;

import util.tester.Tester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1087
 */
public class BraceExpansion {
	/**
	 * SF.
	 */
	public static class S1 {
		private static int ind;

		public String[] expand(String s) {
			ind = 0;
			List<List<Character>> l = new ArrayList<>();
			int i = 0;
			int newn = 1;
			while (i < s.length()) {
				List<Character> curL = new ArrayList<>();
				if (s.charAt(i) == '{') {
					i++;
					while (s.charAt(i) != '}') {
						if (s.charAt(i) != ',') {
							curL.add(s.charAt(i));
						}
						i++;
					}
				} else {
					curL.add(s.charAt(i));
				}
				l.add(curL);
				newn *= curL.size();
				i++;
			}
			String[] res = new String[newn];
			gen(l, 0, res, new char[l.size()]);
			Arrays.sort(res);
			return res;
		}

		private void gen(List<List<Character>> l, int curInd, String[] res, char[] cur) {
			if (curInd == l.size()) {
				res[ind++] = String.valueOf(cur);
			} else {
				for (int j = 0; j < l.get(curInd).size(); j++) {
					cur[curInd] = l.get(curInd).get(j);
					gen(l, curInd + 1, res, cur);
					cur[curInd] = 0;
				}
			}
		}

		public S1() {
		}
	}

	/**
	 * Modification of S1 with counting sort instead of array sort. Faster.
	 */
	public static class S2 {
		private static int ind;

		public String[] expand(String s) {
			ind = 0;
			List<char[]> l = new ArrayList<>();
			int i = 0;
			int newn = 1;
			int[] counts = new int[26];
			while (i < s.length()) {
				int c = 0;
				if (s.charAt(i) == '{') {
					i++;
					while (s.charAt(i) != '}') {
						if (s.charAt(i) != ',') {
							counts[s.charAt(i) - 'a']++;
							c++;
						}
						i++;
					}
				} else {
					counts[s.charAt(i) - 'a']++;
					c++;
				}
				char[] ca = new char[c];
				int j = 0;
				for (int k = 0; k < 26; k++) {
					while (--counts[k] >= 0) {
						ca[j++] = (char) (k + 'a');
					}
					counts[k] = 0;
				}
				l.add(ca);
				newn *= c;
				i++;
			}
			String[] res = new String[newn];
			gen(l, 0, res, new char[l.size()]);
			return res;
		}

		private void gen(List<char[]> l, int curInd, String[] res, char[] cur) {
			if (curInd == l.size()) {
				res[ind++] = String.valueOf(cur);
			} else {
				for (int j = 0; j < l.get(curInd).length; j++) {
					cur[curInd] = l.get(curInd)[j];
					gen(l, curInd + 1, res, cur);
					cur[curInd] = 0;
				}
			}
		}

		public S2() {
		}
	}

	public static void main(String[] args) {
		new Tester(new S1())
		.add("{a,b}c{d,e}f").expect(new String[]{"acdf", "acef", "bcdf", "bcef"})
		.add("{a,b,c}d{e,f}").expect(new String[]{"ade", "adf", "bde", "bdf", "cde", "cdf"})
		.run();
	}
}
