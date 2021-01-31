package regular.hashtable;

import tester.Tester;

import java.util.HashMap;
import java.util.Map;

/**
 * 1357
 *
 * ======
 *
 * Task.
 *
 * There is a sale in a supermarket, there will be a discount every n customer.
 * There are some products in the supermarket where the id of the i-th product
 * is products[i] and the price per unit of this product is prices[i]. The
 * system will count the number of customers and when the n-th customer arrive
 * he/she will have a discount on the bill. (i.e if the cost is x the new cost
 * is x - (discount * x) / 100). Then the system will start counting customers
 * again. The customer orders a certain amount of each product where product[i]
 * is the id of the i-th product the customer ordered and amount[i] is the
 * number of units the customer ordered of that product.
 *
 * ======
 *
 * Source: Leetcode
 */
public class ApplyDiscountEveryNOrders {
	/**
	 * Straightforward.
	 */
	private static class Cashier {
		private int customerCount = 0;
		private int n;
		private int d;
		private Map<Integer, Integer> m = new HashMap<>();

		public Cashier(int n, int discount, int[] p, int[] pr) {
			this.n = n;
			this.d = discount;

			for (int i = 0; i < p.length; i++)
				m.put(p[i], pr[i]);
		}

		public double getBill(int[] p, int[] a) {
			int s = 0;
			for (int i = 0; i < p.length; i++)
				s += m.get(p[i]) * a[i];

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