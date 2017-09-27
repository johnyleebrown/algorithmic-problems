package Easy.Array;

/**
 * 122
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times).
 * However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */
public class BestTimeToBuyAndSellStockII {
    class Solution {
        public int maxProfit(int[] prices) {
            int profit = 0, i = 0;
            while (i < prices.length) {
                // find next local minimum
                while (i < prices.length - 1 && prices[i + 1] <= prices[i]) i++;
                int min = prices[i++]; // need increment to avoid infinite loop for "[1]"
                // find next local maximum
                while (i < prices.length - 1 && prices[i + 1] >= prices[i]) i++;
                profit += i < prices.length ? prices[i++] - min : 0;
            }
            return profit;
        }
    }
}
