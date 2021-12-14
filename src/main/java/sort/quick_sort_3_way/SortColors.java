package sort.quick_sort_3_way;

/**
 * 75
 */
public class SortColors {

	/**
	 * 4 pointers: place_to_insert_0, new_0, new_2, place_to_insert_2
	 */
	public static class Solution {

		public void sortColors(int[] nums) {
			int v0 = 0, v2 = nums.length - 1, l = 0, r = nums.length - 1;
			while (v0 < nums.length || v2 >= 0) {

				// move pointers
				while (l < nums.length && nums[l] == 0) {
					l++;
				}
				while (r >= 0 && nums[r] == 2) {
					r--;
				}
				while (v0 < nums.length && (nums[v0] != 0 || v0 <= l)) {
					v0++;
				}
				while (v2 >= 0 && (nums[v2] != 2 || v2 >= r)) {
					v2--;
				}

				// exchange
				if (l < nums.length && r >= 0 && nums[l] == 2 && nums[r] == 0 && l < r) {
					nums[l++] = 0;
					nums[r--] = 2;
				} else if (l < nums.length && r >= 0 && nums[l] == 2 && l < r) {
					nums[l] = nums[r];
					nums[r] = 2;
					r--;
				} else if (l < nums.length && r >= 0 && nums[r] == 0 && l < r) {
					nums[r] = nums[l];
					nums[l] = 0;
					l++;
				} else {
					if (v0 < nums.length) {
						int t0 = nums[l];
						nums[l] = nums[v0];
						nums[v0] = t0;
						l++;
						v0++;
					}
					if (v2 >= 0) {
						int t2 = nums[r];
						nums[r] = nums[v2];
						nums[v2] = t2;
						r--;
						v2--;
					}
				}
			}
		}
	}

	/**
	 * With exch method
	 */
	public static class Solution2 {

		public void sortColors(int[] nums) {
			int v0 = 0, v2 = nums.length - 1, l = 0, r = nums.length - 1;
			while (v0 < nums.length || v2 >= 0) {
				while (l < nums.length && nums[l] == 0) {
					l++;
				}
				while (r >= 0 && nums[r] == 2) {
					r--;
				}
				while (v0 < nums.length && (nums[v0] != 0 || v0 <= l)) {
					v0++;
				}
				while (v2 >= 0 && (nums[v2] != 2 || v2 >= r)) {
					v2--;
				}

				if (l < nums.length && r >= 0 && l < r) {
					if (nums[l] == 2 && nums[r] == 0) {
						nums[l++] = 0;
						nums[r--] = 2;
					} else if (nums[l] == 2) {
						nums[l] = nums[r];
						nums[r--] = 2;
					} else if (nums[r] == 0) {
						nums[r] = nums[l];
						nums[l++] = 0;
					} else {
						if (exch(nums, l, v0)) {
							l++;
							v0++;
						}
						if (exch(nums, r, v2)) {
							r--;
							v2--;
						}
					}
				}
			}
		}

		boolean exch(int[] nums, int i, int j) {
			if (i < 0 || j < 0 || i >= nums.length || j >= nums.length) return false;
			int t = nums[i];
			nums[i] = nums[j];
			nums[j] = t;
			return true;
		}
	}

	/**
	 * quick sort 3 way partitioning, my version
	 */
	public static class Solution3 {

		public void sortColors(int[] nums) {
			int v = 0, l = 0, r = nums.length - 1;

			while (l < r && v < nums.length) {
				if (nums[l] == 0) l++;
				else if (nums[r] == 2) r--;
				else {
					if (nums[v] == 2) {
						if (v < r) {
							nums[v] = nums[r];
							nums[r--] = 2;
						}
					}
					if (nums[v] == 0) {
						if (v > l) {
							nums[v] = nums[l];
							nums[l++] = 0;
						}
					}
					v++;
				}
			}
		}
	}

	/**
	 * quick sort 3 way partitioning
	 */
	public static class Solution4 {

		public void sortColors(int[] nums) {
			int low = 0, mid = 0, high = nums.length - 1;
			while (mid <= high) {
				if (nums[mid] == 0) exch(nums, low++, mid++);
				else if (nums[mid] == 1) mid++;
				else exch(nums, mid, high--);
			}
		}

		void exch(int[] nums, int i, int j) {
			int t = nums[i];
			nums[i] = nums[j];
			nums[j] = t;
		}
	}
}
