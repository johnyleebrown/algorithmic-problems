package sort._ds;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

	@BeforeEach
	void setUp() {
		System.out.println("[TEST] start");
	}

	@AfterEach
	void tearDown() {
		System.out.println("[TEST] end");
	}

	/**
	 * Check if it is sorted.
	 *
	 * Limit each temp file by 10_000 lines.
	 * Total number in input file is 1M.
	 */
	@Test
	void limit_10K_total_1M() throws IOException {

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
	 * Limit each temp file by 1000K*X lines.
	 * Total number in input file is 1M.
	 */
	@Test
	void limit_1KX_total_1M() throws IOException {

		// generate file
		String inputFileName = "randomNumbers_1M.txt";
		int n = 1_000_000; // number of items
		Generator.generateFileWithNumbers(n, 1_000_000, inputFileName);
		System.out.println("total number of items: " + n);

		// sort
		int limit = new Random().nextInt(n - 1000) + 1000;
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

	private void performChecks(String inputFileName, String resultFileName, ExternalSort e) throws IOException {

		Map<Integer, Integer> resultCounts = new HashMap<>();

		int resultNumberOfItems = 0;
		BufferedReader r = createReader(resultFileName);
		String curLine = r.readLine();
		int prev = Integer.MIN_VALUE;

		System.out.println("checking if next is not smaller than prev..");

		while (curLine != null) {
			int cur = Integer.parseInt(curLine);
			resultCounts.put(cur, resultCounts.getOrDefault(cur, 0) + 1);
			resultNumberOfItems++;

			// check if next is not smaller than prev
			assertTrue(prev <= cur);

			prev = cur;
			curLine = r.readLine();
		}

		// check the data itself
		int inputNumberOfItems = 0;
		Map<Integer, Integer> inputCounts = new HashMap<>();
		BufferedReader inputReader = createReader(inputFileName);
		String inputCurLine = inputReader.readLine();
		while (inputCurLine != null) {
			int cur = Integer.parseInt(inputCurLine);
			inputCounts.put(cur, inputCounts.getOrDefault(cur, 0) + 1);
			inputCurLine = inputReader.readLine();
			inputNumberOfItems++;
		}

		System.out.println("checking sizes..");

		// check sizes
		assertEquals(inputNumberOfItems, resultNumberOfItems);

		System.out.println("checking counts..");
		int batch = resultNumberOfItems / 10;
		int checkedCount = 0;
		for (int resultKey : resultCounts.keySet()) {
			if (checkedCount % batch == 0) System.out.println("checked " + checkedCount + " items");
			assertTrue(inputCounts.containsKey(resultKey));
			assertEquals(inputCounts.get(resultKey), resultCounts.get(resultKey), "wrong: " + resultKey);
			checkedCount++;
		}
	}
}