package tree._ds.SegmentTree;

/**
 * @nochecker
 *
 * RangeSlow
 *
 * Slow version of range increment.
 */
public class SegmentTreeSlow implements SegmentTreeQuery {
	private Integer[] ar;

	public SegmentTreeSlow(int n) {
		ar = new Integer[n];
	}

	public void increment(int i, int j, int val) {
		for (int k = i; k <= j; k++) {
			if (ar[k] == null) {
				ar[k] = 0;
			}
			ar[k] += val;
		}
	}

	public int min(int i, int j) {
		int res = Integer.MAX_VALUE;
		for (int k = i; k <= j; k++) {
			res = Math.min(res, ar[k] == null ? Integer.MAX_VALUE : ar[k]);
		}
		return res;
	}

	public void print() {
		for (int i = 0; i < ar.length; i++) {
			System.out.print(ar[i] + " ");
		}
		System.out.println();
	}

	@Override
	public int max(int i, int j) {
		int res = Integer.MIN_VALUE;
		for (int k = i; k <= j; k++) {
			res = Math.max(res, ar[k] == null ? Integer.MIN_VALUE : ar[k]);
		}
		return res;
	}
}
