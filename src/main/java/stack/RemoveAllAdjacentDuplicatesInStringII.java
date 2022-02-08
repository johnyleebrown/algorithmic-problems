package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 1209
 */
public class RemoveAllAdjacentDuplicatesInStringII {

	/**
	 * Use stack of array of char counts. aabbbc => [{a,2},{b,3},{c,1}]. If we encounter
	 * same char - we modify the count - if it's k - we remove the char from stack.
	 */
	public static class Solution {

		public String removeDuplicates(String s, int k) {
			List<int[]> counts = new ArrayList<>();
			int n = s.length();
			for (int i = 0; i < n; i++) {
				char c = s.charAt(i);
				if (counts.size() == 0 || counts.get(counts.size() - 1)[0] != c) {
					counts.add(new int[]{c, 1});
				} else {
					counts.get(counts.size() - 1)[1]++;
				}
			}
			Stack<int[]> st = new Stack<>();
			for (int[] cur : counts) {
				if (st.isEmpty()) {
					// see if counts fits k, leave leftover if exists
					// if %k==0
					int sum = cur[1];
					int leftover = sum % k;
					if (leftover != 0) {
						cur[1] = leftover;
						st.push(cur);
					}
				} else {
					//if prev is same - sum counts, see above, rem prev
					//if not see above
					int[] prev = st.peek();
					if (prev[0] == cur[0]) {
						int sum = prev[1] + cur[1];
						int leftover = sum % k;
						if (leftover == 0) st.pop();
						else st.peek()[1] = leftover;
					} else {
						int sum = cur[1];
						int leftover = sum % k;
						if (leftover != 0) {
							cur[1] = leftover;
							st.push(cur);
						}
					}
				}
			}
			StringBuilder sb = new StringBuilder();
			for (int[] cur : st) {
				while (--cur[1] >= 0) {
					sb.append((char) cur[0]);
				}
			}
			return sb.toString();
		}
	}

	/**
	 * Solution with replacements (2 pointers) - we move second pointer value closer to i,
	 * so we could compare values - to see if we have dups.
	 */
	public static class Solution2 {

		public String removeDuplicates(String s, int k) {
			int i = 0, n = s.length();
			int[] count = new int[n];
			char[] stack = s.toCharArray();
			for (int j = 0; j < n; ++j, ++i) {
				stack[i] = stack[j];
				count[i] = 1;
				if (i > 0 && stack[i - 1] == stack[j]) {
					count[i] = count[i - 1] + 1;
				}
				if (count[i] == k) {
					i -= k;
				}
			}
			return new String(stack, 0, i);
		}
	}
}