package array.counter;

import java.util.ArrayList;
import java.util.List;

/**
 * 442
 */
public class FindAllDuplicatesInAnArray {

	/**
	 * Loop array, set value at index ar[i] to negative, if we try to do this and encounter
	 * a negative there, add to ans.
	 */
	public static class Solution {

		public List<Integer> findDuplicates(int[] ar) {
			List<Integer> ans = new ArrayList<>();
			for (int i = 0; i < ar.length; i++) {
				int val = Math.abs(ar[i]);
				int ind = val - 1;
				if (ar[ind] < 0) ans.add(val);
				ar[ind] = -ar[ind];
			}
			return ans;
		}
	}
}