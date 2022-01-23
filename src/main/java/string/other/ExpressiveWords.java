package string.other;

/**
 * 809
 */
public class ExpressiveWords {
	public static class Solution {
		public int expressiveWords(String target, String[] ar) {
			int ans = 0;
			char[] t = target.toCharArray();
			for (String s : ar) if (check(s, t)) ans++;
			return ans;
		}

		boolean check(String str, char[] t) {
			if (t.length < str.length()) return false;
			char[] s = str.toCharArray();
			int sn = s.length;
			int tn = t.length;
			int i = 0; // si
			int j = 0; // ti
			while (i < sn) {
				if (s[i] != t[j]) return false;

				int sCurCount = 1;
				while (i + 1 < sn && s[i + 1] == s[i]) {
					sCurCount++;
					i++;
				}
				int tCurCount = 1;
				while (j + 1 < tn && t[j + 1] == t[j]) {
					tCurCount++;
					j++;
				}

				if (!canStretch(sCurCount, tCurCount)) return false;

				i++;
				j++;
			}

			return j == tn;
		}

		boolean canStretch(int sCurCount, int tCurCount) {
			if (sCurCount == tCurCount) return true;
			if (tCurCount < 3) return false;
			if (sCurCount > tCurCount) return false;
			return true;
		}
	}
}