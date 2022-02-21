package array.consecutive;

import java.util.HashMap;
import java.util.Map;

/**
 * 659
 */
public class SplitArrayIntoConsecutiveSubsequences {

	/**
	 * TODO redo damn good problem
	 * - We iterate through the array once to get the frequency of all the
	 * elements in the array
	 * - We iterate through the array once more and for each element we either
	 * see if it can be appended to a previously constructed consecutive
	 * sequence or if it can be the start of a new consecutive sequence. If
	 * neither are true, then we return false.
	 */
	public static class Solution {

		public boolean isPossible(int[] nums) {
			Map<Integer, Integer> freq = new HashMap<>(), appendfreq = new HashMap<>();
			for (int i : nums) {
				freq.put(i, freq.getOrDefault(i, 0) + 1);
			}
			for (int i : nums) {
				if (freq.get(i) == 0) {
					continue;
				}
				// add to existing
				else if (appendfreq.getOrDefault(i, 0) > 0) {
					appendfreq.put(i, appendfreq.get(i) - 1);
					appendfreq.put(i + 1, appendfreq.getOrDefault(i + 1, 0) + 1);
				}
				// add new
				else if (freq.getOrDefault(i + 1, 0) > 0 && freq.getOrDefault(i + 2, 0) > 0) {
					freq.put(i + 1, freq.get(i + 1) - 1);
					freq.put(i + 2, freq.get(i + 2) - 1);
					appendfreq.put(i + 3, appendfreq.getOrDefault(i + 3, 0) + 1);
				} else {
					return false;
				}
				freq.put(i, freq.get(i) - 1);
			}
			return true;
		}
	}
}