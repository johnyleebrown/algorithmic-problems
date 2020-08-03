package backtracking.other;

import util.tester.Tester;

import java.util.*;

/**
 * Google_Interview_2
 *
 * ======
 *
 * Task.
 *
 * You are given a phone number as an array of n digits. To help you memorize
 * the number, you want to divide it into groups of contiguous digits. Each
 * group must contain exactly 2 or 3 digits. There are 3 kinds of groups:
 *
 * Excellent: A group that contains only the same digits. For example, 000 or
 * 77. Good: A group of 3 digits, 2 of which are the same. For example, 030, 229
 * or 166. Usual: A group in which all the digits are distinct. For example, 123
 * or 90. The quality of a group assignment is defined as 2 × (number of
 * excellent groups) + (number of good groups).
 *
 * Divide the phone number into groups such that the quality is maximized.
 */
public class MemorizePhoneNumber {
	private static class Solution1 {
		private List<List<Integer>> ans;
		private int quality = Integer.MIN_VALUE;
		private boolean[] seen;
		private int eg = 0, gg = 0;

		public List<List<Integer>> memorize(int[] number) {
			if (number.length == 0) {
				return null;
			}

			ans = new ArrayList<>();
			seen = new boolean[number.length];

			generate(new ArrayList<>(), number, 0, new ArrayList<>());

			return ans;
		}

		private void generate(List<Integer> cur, int[] a,
		                      int localc, List<List<Integer>> curans) {
			if (localc > 1) {
				g(cur, localc, curans);
			}

			if (hasMaxQuality()) {
				ans = new ArrayList(curans);
			} else {
				for (int i = 0; i < a.length; i++) {
					if (seen[i]) continue;

					seen[i] = true;
					cur.add(a[i]);
					generate(cur, a, localc + 1 == 4 ? 1 : localc + 1, curans);
					generate(cur, a, localc + 1 == 3 ? 1 : localc + 1, curans);
					cur.remove(i);
					seen[i] = false;
				}
			}
		}

		private boolean hasMaxQuality() {
			int x = 2 * eg + gg;
			if (quality < x) {
				quality = x;
				return true;
			}

			return false;
		}

		private void g(List<Integer> cur, int localc, List<List<Integer>> curans) {
			if (localc == 2) {
				if (cur.get(0) == cur.get(1)) {
					eg++;
				}
			} else {
				if (cur.get(0) == cur.get(1) || cur.get(2) == cur.get(1)) {
					if (cur.get(0) == cur.get(2)) {
						eg++;
					} else {
						gg++;
					}
				} else if (cur.get(0) == cur.get(2)) {
					gg++;
				}
			}

			curans.add(new ArrayList(cur));
		}
	}

	/**
	 * todo: add memo.
	 */
	private static class Solution2 {
		int n;
		int[] marks = new int[4];//e,g,u
		int max = 0;
		List<Integer> dels = new ArrayList<>();
		List<Integer> good = new ArrayList<>();

		public List<List<Integer>> sol(int[] a) {
			n = a.length;
			gen(a, 0);
			List<List<Integer>> res = new ArrayList<>();
			int prev = 0;
//			good.add(n);
			for (int i : good) {
				List<Integer> local = new ArrayList<>();
				for (int j = prev; j < i; j++) {
					local.add(a[j]);
				}
//				if (i != n)
				res.add(local);
//				System.out.println(local);
				prev = i;
			}
			return res;
		}

		private void gen(int[] a, int prev) {
			if (maxQuality() && prev == n) {
				good = new ArrayList<>(dels);
			} else {
				if (prev + 1 < n) {
					int group1 = calc(a[prev], a[prev + 1]);
					marks[group1]++;
					dels.add(prev + 2);
					gen(a, prev + 2);
					marks[group1]--;
					dels.remove(dels.size() - 1);
				}
				if (prev + 2 < n) {
					int group2 = calc(a[prev], a[prev + 1], a[prev + 2]);
					marks[group2]++;
					dels.add(prev + 3);
					gen(a, prev + 3);
					marks[group2]--;
					dels.remove(dels.size() - 1);
				}
			}
		}

		private boolean maxQuality() {
			int x = 2 * marks[1] + marks[2];
			if (max < x) {
				max = x;
				return true;
			} else {
				return false;
			}
		}

		private int calc(int... a) {
			Set<Integer> set = new HashSet<>();
			for (int i : a) {
				set.add(i);
			}
			return set.size();
		}
	}

	private static class Solution3 {
		public static String getPhoneNumberPartitation(String phno) {
			int[] arr2 = new int[phno.length()];
			int[] arr3 = new int[phno.length()];
			getPreCnt(phno, arr2, 2);
			getPreCnt(phno, arr3, 3);
			int[][] dp = new int[phno.length()][3];
			dp[1][arr2[1] == 1 ? 2 : 0]++;
			dp[2][arr3[2] == 1 ? 2 : (arr3[2] == 2 ? 1 : 0)]++;
			dp[3][arr2[3] == 1 ? 2 : 0]++;
			int[] prev = new int[phno.length()];
			prev[1] = 2;
			prev[3] = 2;
			for (int i = 4; i < phno.length(); i++) {
				int cur2 = arr2[i] == 1 ? 2 : 0;
				int from2 = 0;
				if (cur2 == 0)
					from2 = dp[i - 2][0] + dp[i - 2][1] + 2 * dp[i - 2][2];
				else
					from2 = dp[i - 2][0] + dp[i - 2][1] + 2 * (dp[i - 2][2] + 1);

				int cur3 = arr3[i] == 1 ? 2 : (arr3[i] == 2 ? 1 : 0);
				int from3 = 0;
				if (cur3 == 0)
					from3 = dp[i - 3][0] + dp[i - 3][1] + 2 * dp[i - 3][2];
				else if (cur3 == 1)
					from3 = dp[i - 3][0] + dp[i - 3][1] + 1 + 2 * dp[i - 3][2];
				else
					from3 = dp[i - 3][0] + dp[i - 3][1] + 2 * (dp[i - 3][2] + 1);

				if (from2 > from3) {
					dp[i][cur2] = dp[i - 2][cur2] + 1;
					for (int j = 0; j < 3 && j != cur2; j++) {
						dp[i - 2][j] = dp[i][j];
					}
					prev[i] = 2;
				} else {
					dp[i][cur3] = dp[i - 3][cur3] + 1;
					for (int j = 0; j < 3 && j != cur3; j++) {
						dp[i - 3][j] = dp[i][j];
					}
					prev[i] = 3;
				}
			}
			StringBuilder sb = new StringBuilder();
			for (int i = prev.length - 1; i > 0; ) {
				int preSteps = prev[i];
				sb.insert(0, ')');
				while (preSteps > 0) {
					sb.insert(0, phno.charAt(i--));
					preSteps--;
				}
				sb.insert(0, '(');
			}
			return sb.toString();
		}

		private static void getPreCnt(String phno, int[] arr, int dist) {
			int[] numCnt = new int[10];
			int cnt = 0;
			int l = 0;
			for (int r = 0; r < phno.length(); r++) {
				char c = phno.charAt(r);
				if (numCnt[c - '0'] == 0) {
					cnt++;
				}
				numCnt[c - '0']++;
				while (r - l > dist - 1) {
					numCnt[phno.charAt(l) - '0']--;
					if (numCnt[phno.charAt(l) - '0'] == 0)
						cnt--;
					l++;
				}
				if (r > dist - 2)
					arr[r] = cnt;
			}
		}
	}

	public static void main(String[] args) {

		List<List<Integer>> l = new ArrayList<>();
		//(12)(33)(44)(555)(66)
		l.add(Arrays.asList(1, 2));
		l.add(Arrays.asList(3, 3));
		l.add(Arrays.asList(4, 4));
		l.add(Arrays.asList(5, 5, 5));
		l.add(Arrays.asList(6, 6));
		new Tester(new Solution1())
		.add(new int[]{1, 2, 3, 3, 4, 4, 5, 5, 5, 6, 6}).expect(l)
		.run();
		/*
		List<List<Integer>> l1 = new ArrayList<>();
		//(12)(33)(44)(55)(56)
		l1.add(Arrays.asList(1,2));
		l1.add(Arrays.asList(3,3));
		l1.add(Arrays.asList(4,4));
		l1.add(Arrays.asList(5,5));
		l1.add(Arrays.asList(5,6));
		new Tester(new Solution1())
				.add(new int[]{1,2,3,3,4,4,5,5,5,6}).expect(l1)
				.run();
*/
		/*
		List<List<Integer>> l2 = new ArrayList<>();
		l2.add(Arrays.asList(7,7,4));
		l2.add(Arrays.asList(7,7,4));



		//774-774
		List<List<Integer>> l3 = new ArrayList<>();
		l3.add(Arrays.asList(7,7));
		l3.add(Arrays.asList(4,7));
		l3.add(Arrays.asList(7,4));
		// 77-47-74
		new Tester(new Solution1())
				.add(new int[]{7,7,4,7,7,4}).expect(l2).orExpect(l3)
				.run();
 */

		new Tester(new Solution3())
		.add("1233445556").expect("(12)(33)(44)(55)(56)")
		.run();
	}
}