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
			int planted = 0;
			for (int i = 0; i < flowerbed.length; i++) {
				if (flowerbed[i] != 1 && (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
					planted += 1;
					if (planted >= n) {
						return true;
					}
					i += 1;
				}
			}
			return false;
		}
	}
}
