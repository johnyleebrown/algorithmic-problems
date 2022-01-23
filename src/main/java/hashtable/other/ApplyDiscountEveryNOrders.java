package hashtable.other;

import java.util.HashMap;
import java.util.Map;

/**
 * 1357
 */
public class ApplyDiscountEveryNOrders {

	/**
	 * SF.
	 */
	private static class Cashier {

		private int customerCount = 0;
		private int n;
		private int d;
		private Map<Integer, Integer> m = new HashMap<>();

		public Cashier(int n, int discount, int[] p, int[] pr) {
			this.n = n;
			this.d = discount;

			for (int i = 0; i < p.length; i++) {
				m.put(p[i], pr[i]);
			}
		}

		public double getBill(int[] p, int[] a) {
			int s = 0;
			for (int i = 0; i < p.length; i++) {
				s += m.get(p[i]) * a[i];
			}

			customerCount++;
			if (customerCount % n == 0)
				return getSumWithPriceCut(s);

			else return s;
		}

		private double getSumWithPriceCut(double x) {
			return x - (d * x) / 100;
		}
	}
}