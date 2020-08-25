package regular.array;

/**
 * 605
 */
public class CanPlaceFlowers {
	public static class Solution {
		public boolean canPlaceFlowers(int[] flowerbed, int n) {
			if (n == 0) {
				return true;
			}
			if (flowerbed.length == 1 && flowerbed[0] == 0 && n == 1) {
				return true;
			}
			int i = 0;
			int count = 0;
			while (i < flowerbed.length) {
				if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
					flowerbed[i++] = 1;
					count++;
				}
				if (count >= n) {
					return true;
				}
				i++;
			}
			return false;
		}
	}
}
