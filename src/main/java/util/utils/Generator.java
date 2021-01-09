package util.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static util.utils.reader.IOUtils.createWriter;

public class Generator {

	/**
	 * @param n     number of items to generate
	 * @param bound max number
	 */
	public static void generateFileWithNumbers(int n, int bound, String fileName) throws IOException {
		Random r = new Random();
		BufferedWriter w = createWriter(fileName);
		for (int j = 0; j < n; j++) {
			int num = r.nextInt(bound);
			w.write(String.valueOf(num));
			w.newLine();
		}
		w.close();
	}

	public static int[] genIntArray(int n, int randomBoundary, boolean canRep) {
		final Random r = new Random();
		Set<Integer> set = new HashSet<>();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			int x = r.nextInt(randomBoundary);
			if (!canRep) {
				while (!set.add(x)) {
					x = r.nextInt(randomBoundary);
				}
			}
			a[i] = x;
		}
		return a;
	}

	@SuppressWarnings({"unchecked"})
	public static <T> T[] genArray(int n, int randomBoundary) {
		final Random r = new Random();
		Set<T> set = new HashSet<>();
		T[] a = (T[]) new Object[n];
		for (int i = 0; i < n; i++) {
			T x = getTFromInt(r, randomBoundary);
			while (!set.add(x)) {
				x = getTFromInt(r, randomBoundary);
			}
			a[i] = x;
		}
		return a;
	}

	@SuppressWarnings({"unchecked"})
	private static <T> T getTFromInt(Random r, int randomBoundary) {
		return (T) new Integer(r.nextInt(randomBoundary));
	}

	public static class Two {
		public static int[][] intAr(int n, int m, int randBoundary) {
			Random r = new Random();
			int[][] a = new int[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					a[i][j] = r.nextInt(randBoundary);
				}
			}
			System.out.println("[done] - generation");
			return a;
		}
	}

	public static String createRandomReadableString(int size) {
		return createRandomReadableStringBuilder(size).toString();
	}

	public static StringBuilder createRandomReadableStringBuilder(int size) {
		Random r = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			// adding 33 cuz first 33 are not visible
			sb.append((char) (r.nextInt(255 - 33) + 33));
		}
		return sb;
	}
}
