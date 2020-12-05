package regular.string;

/**
 * 344
 */
public class ReverseString {
	class Solution {
		public void reverseString(char[] s) {
			helper(0, s.length, s);
		}

		private void helper(int i, int n, char[] s) {
			// base case is when we crossed the middle point - n=4, n/2=2(ok), n=5, n/2=2(ok)
			if (i == n / 2) return;

			// swap
			char temp = s[i];
			s[i] = s[n - 1 - i];
			s[n - 1 - i] = temp;

			helper(i + 1, n, s);
		}
	}

	// O(n/2), O(n)
	class Solution2 {
		public String reverseString(String s) {
			if (s.length() < 2) return s;
			int l = 0;
			int r = s.length() - 1;
			char[] letters = s.toCharArray();
			while (l < r) {
				char temp = letters[l];
				letters[l] = letters[r];
				letters[r] = temp;
				l++;
				r--;
			}
			return new String(letters);
		}
	}
}
