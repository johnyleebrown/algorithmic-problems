package util.utils;

import java.util.LinkedList;
import java.util.List;

public class ArrayUtils {

	/**
	 * Get new index after jump in circular array.
	 */
	public static int getNewIndex(int curInd, int jump, int arLen) {
		if (jump < 0) {
			// n=7 i=2 j=-13
			// 1 way
			int x = (curInd + jump) % arLen;
			if (x < 0) x += arLen;
			return x;
			/*
			2 way
			return (((curInd + jump) % arLen) + arLen) % arLen;
			 */
		} else {
			// n=7 i=2 j=13
			return (curInd + jump) % arLen;
		}
	}

	public static int[][] parseString2dAr(String source) {
		String[] ar = source.substring(1, source.length() - 1).split("]");
		int[][] ans = new int[ar.length][];
		int i = 0;
		for (String s : ar) {
			String[] nums = s.split("[^0-9]");
			List<Integer> loc = new LinkedList<>();
			for (String n : nums) {
				if (!n.trim().isEmpty()) {
					loc.add(Integer.parseInt(n));
				}
			}
			int[] a = new int[loc.size()];
			int k = 0;
			for (int j : loc) {
				a[k++] = j;
			}
			ans[i++] = a;
		}
		return ans;
	}

	public static void exch(int[] a, int j, int i) {
		int swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
}
