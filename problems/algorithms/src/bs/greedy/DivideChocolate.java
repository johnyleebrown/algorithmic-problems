package bs.greedy;

/**
 * 1231
 *
 * ======
 *
 * Task.
 *
 * You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given
 * by the array sweetness.
 *
 * You want to share the chocolate with your K friends so you start cutting the chocolate bar into
 * K+1 pieces using K cuts, each piece consists of some consecutive chunks.
 *
 * Being generous, you will eat the piece with the minimum total sweetness and give the other pieces
 * to your friends.
 *
 * Find the maximum total sweetness of the piece you can get by cutting the chocolate bar
 * optimally.
 *
 * ======
 *
 * Source: Leetcode
 */
public class DivideChocolate {
	/**
	 * Using binary search pick the piece with the right amount of sweetness greedily.
	 */
	public static class Solution {
		public int maximizeSweetness(int[] ar, int k) {

			// we want k + 1 pieces cuz me + k friends
			k = k + 1;

			// get lo and hi
			int lo = 1;
			// hi can be > 1e5
			int sum = 0;
			for (int i : ar) {
				sum += i;
			}
			int hi = Math.max(100_000, sum);

			while (hi - lo >= 0) {
				// the amount of sweetness for myself
				int myPieceSweetness = lo + (hi - lo) / 2;

				// check how many pieces we can cut into with that sweetness in mind
				// in pieces of total sw >= mid
				int x = countPiecesWithMinCut(myPieceSweetness, ar);

				// good case, a lot of pieces - can afford to increase our cut
				if (x >= k) {
					lo = myPieceSweetness + 1;
				}
				// bad, decrease our cut
				else {
					hi = myPieceSweetness - 1;
				}
			}

			// we return hi because we want to max our cut
			return hi;
		}

		/**
		 * How many pieces we can get greedily having a min cut. A min cut is a sweetness of our
		 * piece, all other pieces have to have more sweetness in them.
		 */
		private int countPiecesWithMinCut(int minimumCut, int[] ar) {
			int count = 0;
			int curPiece = 0;
			for (int s : ar) {
				curPiece += s;
				if (curPiece >= minimumCut) {
					curPiece = 0;
					count++;
				}
			}
			return count;
		}
	}
}