package queue.monotonicQueue;

import queue.monotonicQueue.ds.DecreasingMonotonicQueueNearestValues;
import queue.monotonicQueue.ds.impl.DecreasingMonotonicQueueNearestValuesImpl;

/**
 * 901
 *
 * ======
 *
 * Task.
 *
 * Write a class StockSpanner which collects daily price quotes for some stock, and returns the span of that stock's
 * price for the current day.
 *
 * The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and
 * going backwards) for which the price of the stock was less than or equal to today's price.
 *
 * For example, if the price of a stock over the next 7 days were [100, 80, 60, 70, 60, 75, 85], then the stock spans
 * would be [1, 1, 1, 2, 1, 4, 6].
 *
 * ======
 *
 * Source: Leetcode
 */
public class OnlineStockSpan
{
	/**
	 * TLE smh
	 *
	 * inp: [100, 80, 60, 70, 60, 75, 85]
	 * dec: [100], [100,80], [100,80,60], [100,80,70], [100,80,70,60], [100,80,75], [100,85]
	 * res: [1, 1, 1, 2, 1, 4, 6]
	 */
	static class Solution
	{
		static class StockSpanner
		{
			private DecreasingMonotonicQueueNearestValues q;
			private int index;

			public StockSpanner()
			{
				// default nearest value is -1
				// so we could count the i-th bar itself
				q = new DecreasingMonotonicQueueNearestValuesImpl(-1);
				index = -1;
			}

			public int next(int price)
			{
				index++;
				q.push(price, index);
				return index - q.getNearestValueBiggerThanAtIndex(index);
			}
		}
	}
}