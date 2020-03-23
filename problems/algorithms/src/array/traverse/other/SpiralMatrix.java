package array.traverse.other;

import java.util.LinkedList;
import java.util.List;

/**
 * 54
 */
public class SpiralMatrix
{
	class Solution {
		public List<Integer> spiralOrder(int[][] a) {
			List<Integer> res = new LinkedList<>();
			int n = a.length;
			if (n == 0) {
				return res;
			}
			int m = a[0].length;
			if (m == 0) {
				return res;
			}
			int l = 0;//3
			int r = m - 1;//1
			int t = 1;//4
			int b = n - 1;//2
			int max = n*m;
			int dir = 1;
			int i = 0;
			int j = 0;
			while (max > 0) {
				if (dir == 1) {
					while (j <= r) {
						res.add(a[i][j]);
						j++;
						max--;
					}
					dir = 2;
					r--;
					i++;
					j--;
				} else if (dir == 2) {
					while (i <= b) {
						res.add(a[i][j]);
						i++;
						max--;
					}
					dir = 3;
					b--;
					j--;
					i--;
				} else if (dir == 3) {
					while (j >= l) {
						res.add(a[i][j]);
						j--;
						max--;
					}
					dir = 4;
					l++;
					i--;
					j++;
				} else {
					while (i >= t) {
						res.add(a[i][j]);
						i--;
						max--;
					}
					dir = 1;
					t++;
					j++;
					i++;
				}
			}
			return res;
		}
	}
}
