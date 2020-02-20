package unionFind;

/**
 * 765
 *
 * Tests.
 *
 * [1,4,0,5,8,7,6,3,2,9] [11,4,0,5,8,10,6,3,2,9,7,1] [0,2,1,3] [3,2,0,1]
 */
public class CouplesHoldingHands
{
	class Solution
	{
		public int minSwapsCouples(int[] row)
		{
			int c = 0;
			int[] temp = new int[row.length];

			for (int i = 0; i < row.length; i += 2)
			{
				temp[row[i]] = temp[row[i + 1]] = -1;
				if (sanityCheck(row[i], row[i + 1])) continue;
				temp[row[i]] = i;
				temp[row[i + 1]] = i + 1;
			}

			for (int key = 0; key < row.length; key++)
			{
				int curVal = temp[key];
				if (curVal == -1) continue;

				int nbr = getInd(key);
				int nbrVal = temp[nbr];

				if (sanityCheck(curVal, nbrVal)) continue;

				int swapWith = getInd(curVal);

				temp[row[swapWith]] = nbrVal;
				temp[nbr] = -1;

				swap(row, nbrVal, swapWith);

				c++;
			}

			return c;
		}

		private int getInd(int curVal)
		{
			return curVal % 2 == 0 ? curVal + 1 : curVal - 1;
		}

		private boolean sanityCheck(int curVal, int nbrVal)
		{
			return nbrVal == getInd(curVal);
		}

		private void swap(int[] a, int b, int c)
		{
			int t = a[b];
			a[b] = a[c];
			a[c] = t;
		}
	}
}