package sort._ds.externalSort;

import org.junit.jupiter.api.Test;
import util.utils.Generator;
import util.utils.reader.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.utils.reader.IOUtils.createReader;

class ExternalSortTest {

	/**
	 * Check if it is sorted.
	 *
	 * Limit each temp file by 1000 lines.
	 * Total number in input file is 1M.
	 */
	@Test
	void limit_1K_total_1M() throws IOException {

		// generate file
		String inputFileName = "randomNumbers_1M.txt";
		int n = 1_000_000; // number of items
		Generator.generateFileWithNumbers(n, 1_000_000, inputFileName);
		System.out.println("total number of items: " + n);

		// sort
		int limit = 10_000;
		System.out.println("limit: " + limit);

		ExternalSort e = new ExternalSort(inputFileName, limit);
		String resultFileName = e.sort();

		// checks
		performChecks(inputFileName, resultFileName, e);

		// cleanup
		IOUtils.removeFile(inputFileName);
		IOUtils.removeFile(resultFileName);
	}

	/**
	 * Check if it is sorted.
	 *
	 * Limit each temp file by 1000 lines.
	 * Total number in input file is 1M.
	 */
	@Test
	void limit_100_total_1K() throws IOException {

		// generate file
		String inputFileName = "randomNumbers_1K.txt";
		int n = 1_000; // number of items
		Generator.generateFileWithNumbers(n, 1_000_000, inputFileName);
		System.out.println("total number of items: " + n);

		// sort
		int limit = 100;
		System.out.println("limit: " + limit);

		ExternalSort e = new ExternalSort(inputFileName, limit);
		String resultFileName = e.sort();

		// checks
		performChecks(inputFileName, resultFileName, e);

		// cleanup
		IOUtils.removeFile(inputFileName);
		IOUtils.removeFile(resultFileName);
	}

	/**
	 * Check if it is sorted
	 * - num%limit=X. X < 1K.
	 *
	 * Limit each temp file by 1000 lines.
	 * Total number in input file is 1M.
	 */
	@Test
	void limit_X_total_1K() throws IOException {

		// generate file
		String inputFileName = "randomNumbers_1K.txt";
		int n = 1_000; // number of items
		Generator.generateFileWithNumbers(n, 1_000_000, inputFileName);
		System.out.println("total number of items: " + n);

		// sort
		int limit = new Random().nextInt(n);
		System.out.println("limit: " + limit);

		ExternalSort e = new ExternalSort(inputFileName, limit);
		String resultFileName = e.sort();

		// checks
		performChecks(inputFileName, resultFileName, e);

		// cleanup
		IOUtils.removeFile(inputFileName);
		IOUtils.removeFile(resultFileName);
	}

	private Map<Integer, Integer> getInputCounts(String input) throws IOException {
		Map<Integer, Integer> counts = new HashMap<>();
		BufferedReader r = createReader(input);
		String curLine = r.readLine();
		while (curLine != null) {
			int cur = Integer.parseInt(curLine);
			counts.put(cur, counts.getOrDefault(cur, 0) + 1);
			curLine = r.readLine();
		}
		return counts;
	}

	private void performChecks(String inputFileName, String resultFileName, ExternalSort e) throws IOException {

		Map<Integer, Integer> resultCounts = new HashMap<>();

		int resultNumberOfItems = 0;
		BufferedReader r = createReader(resultFileName);
		String curLine = r.readLine();
		int prev = Integer.MIN_VALUE;

		while (curLine != null) {
			int cur = Integer.parseInt(curLine);
			resultCounts.put(cur, resultCounts.getOrDefault(cur, 0) + 1);
			resultNumberOfItems++;

			// check if next is not smaller than prev
			assertTrue(prev <= cur);

			prev = cur;
			curLine = r.readLine();
		}

		// check counts
		assertEquals(e.getInputNumberOfItems(), resultNumberOfItems);

		int batch = resultNumberOfItems / 10;

		// check the data itself
		Map<Integer, Integer> inputCounts = getInputCounts(inputFileName);
		int checkedCount = 0;
		for (int resultKey : resultCounts.keySet()) {
			if (checkedCount % batch == 0) System.out.println("checked " + checkedCount + " items");
			assertTrue(inputCounts.containsKey(resultKey));
			assertEquals(inputCounts.get(resultKey), resultCounts.get(resultKey), "wrong: " + resultKey);
			checkedCount++;
		}
	}
}