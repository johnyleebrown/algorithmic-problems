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
}
