package array;

/**
 * @company PayPay
 * @desc Given an array of integers a, your task is to calculate the digits that occur the
 * most number of times in the array. Return the array of these digits in ascending
 * order.
 */
public class FindPopularDigits {

	/**
	 * Preprocess all numbers up to a 100 into a map where each number maps to 2 digits it
	 * has. Then go through initial array and increment counts for those numbers.
	 */
	public static class Solution {

		int[] solution(int[] a) {
			int[][] dict = new int[100][2];
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					int num = i * 10 + j;
					dict[num][0] = i;
					dict[num][1] = j;
				}
			}
			int[] count = new int[10];
			for (int i : a) {
				int num1 = dict[i][0];
				if (num1 != 0) count[num1]++;
				int num2 = dict[i][1];
				count[num2]++;
			}
			int max = 0, countMax = 0;
			for (int i : count) {
				if (i > max) {
					max = i;
					countMax = 1;
				} else if (i == max) {
					countMax++;
				}
			}
			int[] res = new int[countMax];
			for (int i = 0, j = 0; i < 10; i++) {
				if (count[i] == max) {
					res[j++] = i;
				}
			}
			return res;
		}

	}
}
