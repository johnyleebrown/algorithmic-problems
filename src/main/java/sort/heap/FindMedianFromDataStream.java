package sort.heap;

import java.util.PriorityQueue;

/**
 * 295
 *
 * ======
 *
 * Task.
 *
 * Median is the middle value in an ordered integer list. If the size of the
 * list is even, there is no middle value. So the median is the mean of the two
 * middle value.
 * For example,
 *
 * [2,3,4], the median is 3
 *
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Design a data structure that supports the following two operations:
 *
 * void addNum(int num) - Add a integer number from the data stream to the data
 * structure.
 * double findMedian() - Return the median of all elements so far.
 *
 * ======
 *
 * Source: Leetcode
 */
public class FindMedianFromDataStream {
	/**
	 * Keep 2 PQs - each corresponds to half of the stream:
	 * max: 5 4 3 2 1
	 * min: 6 7 8 9 10
	 *
	 * 1 case - add 3
	 * max 1
	 * min 2
	 * max 3 1
	 * min 2
	 * max 1
	 * min 2 3
	 *
	 * 2 case - add 4
	 * max 1
	 * min 2 3
	 * max 1
	 * min 2 3 4
	 * max 4 1
	 * min 2 3
	 */
	public static class Solution {
		public static class MedianFinder {

			PriorityQueue<Integer> min, max;

			public MedianFinder() {
				min = new PriorityQueue<>((a, b) -> a - b);
				max = new PriorityQueue<>((a, b) -> b - a);
			}

			public void addNum(int num) {
				if (min.size() > max.size()) {
					min.add(num);
					max.add(min.poll());
				} else {
					max.add(num);
					min.add(max.poll());
				}
			}

			public double findMedian() {
				if (min.size() == max.size()) {
					return (((double) min.peek()) + ((double) max.peek())) / 2;
				} else {
					return (double) min.peek();
				}
			}
		}
	}
}