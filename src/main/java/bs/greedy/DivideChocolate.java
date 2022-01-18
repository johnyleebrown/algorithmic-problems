package bs.greedy;

/**
 * 1231
 */
public class DivideChocolate {

	/**
	 * Using binary search pick the piece with the right amount of sweetness greedily.
	 */
	public static class Solution {

		public int maximizeSweetness(int[] chocolateBar, int k) {

			// we want k + 1 pieces cuz yourself + k friends
			k = k + 1;

			// get lo and hi
			int lo = 1;
			// hi can be > 1e5
			int sum = 0;
			for (int i : chocolateBar) {
				sum += i;
			}
			int hi = Math.max(100_000, sum);

			while (hi - lo >= 0) {
				// the amount of sweetness for myself
				int myPieceSweetness = lo + (hi - lo) / 2;

				// check how many pieces we can cut into with that sweetness in mind
				// in pieces of total sw >= mid
				int piecesCount = countPiecesWithMinCut(myPieceSweetness, chocolateBar);

				// good case, a lot of pieces - can afford to increase our cut
				if (piecesCount >= k) {
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
		 * How many pieces we can get greedily having a min cut. A min cut is a sweetness of
		 * our
		 * piece, all other pieces have to have more sweetness in them.
		 */
		private int countPiecesWithMinCut(int minimumCut, int[] chocolateBar) {
			int count = 0;
			int curPiece = 0;
			for (int sweetness : chocolateBar) {
				curPiece += sweetness;
				if (curPiece >= minimumCut) {
					curPiece = 0;
					count++;
				}
			}
			return count;
		}
	}
}