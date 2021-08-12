import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 954. Array of Doubled Pairs https://leetcode.com/problems/array-of-doubled-pairs/
 */
public class ArrayOfDoubledPairs {
	/**
	 * [4,-2,2,-4] -> [-2, 2, 4, -4]
	 */
	public static class Solution {
		public boolean canReorderDoubled(int[] arra) {
			int[] arr = Arrays.stream(arra)
			                  .boxed()
			                  .sorted((a, b) -> Math.abs(a) - Math.abs(b))
			                  .mapToInt(Integer::intValue)
			                  .toArray();
			Map<Integer, Integer> map = new HashMap<>();
			int c = arr.length / 2;
			for (int i = 0; i < arr.length; i++) {
				int other = arr[i] / 2;
				if (arr[i] % 2 == 0 && map.containsKey(other) && map.get(other) > 0) {
					map.put(other, map.get(other) - 1);
					c--;
				} else {
					map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
				}
			}
			return c == 0;
		}
	}

	/**
	 * Count pairs from the minimum
	 */
	public static class Solution2 {
		public boolean canReorderDoubled(int[] arr) {
			int[] counts = new int[1000001];
			int[] countsNegative = new int[1000001];
			int min = Integer.MAX_VALUE;
			int minNegative = Integer.MAX_VALUE;
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] >= 0) {
					counts[arr[i]]++;
					min = Math.min(min, arr[i]);
				} else {
					int x = Math.abs(arr[i]);
					countsNegative[x]++;
					minNegative = Math.min(minNegative, x);
				}
			}
			for (int i = 0; i < counts.length; i++) {
				while (--counts[i] >= 0) {
					int x = i * 2;
					if (counts[x] <= 0) return false;
					counts[x]--;
				}
				while (--countsNegative[i] >= 0) {
					int x = i * 2;
					if (countsNegative[x] <= 0) return false;
					countsNegative[x]--;
				}
			}
			return true;
		}
	}

	public static class Solution3 {
		public boolean canReorderDoubled(int[] arr) {
			int[] counts = new int[1000001];
			int[] countsNegative = new int[1000001];
			int min = Integer.MAX_VALUE;
			int minNegative = Integer.MAX_VALUE;
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] >= 0) {
					counts[arr[i]]++;
					min = Math.min(min, arr[i]);
				} else {
					int x = Math.abs(arr[i]);
					countsNegative[x]++;
					minNegative = Math.min(minNegative, x);
				}
			}
			for (int i = Math.min(min, minNegative); i < counts.length; i++) {
				if (counts[i] > 0) {
					int other = i * 2;
					if (counts[other] < counts[i]) return false;
					counts[other] -= counts[i];
				}
				if (countsNegative[i] > 0) {
					int otherNegative = i * 2;
					if (countsNegative[otherNegative] < countsNegative[i]) return false;
					countsNegative[otherNegative] -= countsNegative[i];
				}
			}
			return true;
		}
	}
}
