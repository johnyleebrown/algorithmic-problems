package util.tester;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TesterComparator {
	private final List<Object> results;
	private final List<Object> expectations;
	private final List<Object> orExpectations;
	private final boolean EXPECT_ANY_ORDER_FLAG;

	public TesterComparator(final List<Object> results, final List<Object> expectations,
	                        final List<Object> orExpectations, final boolean flag) {
		this.results = results;
		this.expectations = expectations;
		this.orExpectations = orExpectations;
		EXPECT_ANY_ORDER_FLAG = flag;
	}

	private boolean areAnagrams(int[] a, int[] b) {
		Map<Integer, Integer> c = new HashMap<>();
		for (int i : a)
			c.put(i, c.getOrDefault(i, 0) + 1);
		for (int i : b)
			c.put(i, c.getOrDefault(i, 0) - 1);
		for (int i : c.keySet())
			if (c.get(i) != 0)
				return false;
		return true;
	}

	public boolean compareResults(int i) {
		if (is2dArr(results.get(i)) || isArr(results.get(i))) {
			return verifyResultArrays(i);
		}

		return verifyResult(i);
	}

	private boolean verifyResult(int i) {
		return compareResult(i, expectations) || compareResult(i, orExpectations);
	}

	private boolean compareResult(int i, List<Object> compareWith) {
		if (compareWith.isEmpty())
			return false;

		return compareWith.get(i).equals(results.get(i));
	}

	private boolean verifyResultArrays(int i) {
		return compareResultArrays(i, expectations) || compareResultArrays(i, orExpectations);
	}

	private boolean compareResultArrays(int i, List<Object> compareWith) {
		if (compareWith.isEmpty())
			return false;
		if (is2dArr(results.get(i)))
			return compare2dArrays(i, compareWith);
		return compareArrays(i, compareWith) || checkForAnagrams(i, compareWith);
	}

	private boolean checkForAnagrams(int i, List<Object> other) {
		return EXPECT_ANY_ORDER_FLAG && areAnagrams((int[]) results.get(i), (int[]) other.get(i));
	}

	private boolean compare2dArrays(int i, List<Object> other) {
		String type = results.get(i).getClass().getSimpleName();
		if (type.equals("String[][]")) {
			return Arrays.deepEquals((String[][]) results.get(i), (String[][]) other.get(i));
		}
		else if (type.equals("int[][]")) {
			return Arrays.deepEquals((int[][]) results.get(i), (int[][]) other.get(i));
		}

		return Arrays.deepEquals(new Object[]{results.get(i)}, (Object[]) other.get(i));
	}

	/**
	 * TODO cast smarter
	 */
	private boolean compareArrays(int i, List<Object> other) {
		String type = results.get(i).getClass().getSimpleName();
		if (type.equals("String[]")) {
			return Arrays.equals((String[]) results.get(i), (String[]) other.get(i));
		}
		else if (type.equals("int[]")) {
			return Arrays.equals((int[]) results.get(i), (int[]) other.get(i));
		}

		return Arrays.equals(new Object[]{results.get(i)}, (Object[]) other.get(i));
	}

	private boolean isIntArr(Object o) {
		return o.getClass().getSimpleName().equals("int[]");
	}

	private boolean is2dIntArr(Object o) {
		return o.getClass().getSimpleName().equals("int[][]");
	}

	private boolean isArr(Object o) {
		return o.getClass().getSimpleName().endsWith("[]");
	}

	private boolean is2dArr(Object o) {
		return o.getClass().getSimpleName().endsWith("[][]");
	}
}
