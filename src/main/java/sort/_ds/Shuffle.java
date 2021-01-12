package sort._ds;

/**
 * The Knuth (or Fisher-Yates) shuffling algorithm guarantees to rearrange the
 * elements in uniformly random order, under the assumption that Math.random()
 * generates independent and uniformly distributed numbers between 0 and 1.
 */
public class Shuffle {
	public static void shuffleArray(Object[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			// choose index uniformly in [0, i]
			int r = (int) (Math.random() * (i + 1));
			Object swap = a[r];
			a[r] = a[i];
			a[i] = swap;
		}
	}

	public static void shuffleIntArray(int[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			// choose index uniformly in [0, i]
			int r = (int) (Math.random() * (i + 1));
			int swap = a[r];
			a[r] = a[i];
			a[i] = swap;
		}
	}
}
