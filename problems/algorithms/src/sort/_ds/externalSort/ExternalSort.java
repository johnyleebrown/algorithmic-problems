package sort._ds.externalSort;

import java.io.*;
import java.util.*;

import static util.utils.reader.IOUtils.*;

/**
 * ExternalSort
 *
 * https://neerc.ifmo.ru/wiki/index.php?title=Алгоритмы_во_внешней_памяти._Базовые_конструкции
 *
 * TODO
 * - remove files after
 * - more result checks (check if numbers are the same)
 * - replace limit with file sizes limit
 */
public class ExternalSort {

	private static final String FILE_EXTENSION = ".txt";
	private static final boolean DEBUGGER_ON = true;

	private final BufferedReader r;
	private final int limit; // lines per temp file limit
	private int inputNumberOfItems;

	public ExternalSort(String fileName, int limit) throws FileNotFoundException {
		inputNumberOfItems = 0;
		this.limit = limit;
		r = createReader(fileName);
	}

	/**
	 * @return name of the result file
	 */
	public String sort() throws IOException {

		writeStatus("=== START ===");

		// split into temp files - O(N)
		// produces lines/limit + (lines%limit==0?0:1) files
		int tempFileCount = splitIntoTempFiles();

		// sort every temp file in mem - quick sort or insertion sort
		// we sort it in memory because this is why we doin splitting in the first place
		Deque<String> files = sortFiles(tempFileCount);

		// merge every 2 files into new one, remove old 2
		// do that until there is 1 file left
		mergeSortedFiles(files);

		writeStatus("=== END ===");

		return files.remove();
	}

	/**************************************************************************/

	private int splitIntoTempFiles() throws IOException {

		writeStatus("[SPLITTING] start");

		int fileCount = 0;
		String cur = r.readLine();

		while (cur != null) {
			writeStatus("creating " + fileCount + "t");
			BufferedWriter w = createWriter(fileCount + "t");
			int batchSize = 0;
			while (cur != null && batchSize != limit) {
				w.write(cur);
				w.newLine();
				cur = r.readLine();
				batchSize++;
			}
			inputNumberOfItems += batchSize;
			w.close();
			cur = r.readLine();
			fileCount++;
		}

		writeStatus("[SPLITTING] end");

		return fileCount;
	}

	private Deque<String> sortFiles(int tempFileCount) throws IOException {

		writeStatus("[SORTING] start");

		Deque<String> files = new ArrayDeque<>();

		for (int i = 0; i < tempFileCount; i++) {

			String fileName = i + "t";
			files.addLast(fileName);

			// get data
			List<String> data = readFile(fileName);

			// file array
			int[] ar = new int[data.size()];
			int k = 0;
			for (String d : data) {
				ar[k++] = getInt(d);
			}

			// sort array
			Arrays.sort(ar);

			// write
			for (int j = 0; j < ar.length; j++) {
				data.set(j, String.valueOf(ar[j]));
			}
			writeFile(fileName, data);

			writeStatus("sorted " + fileName);
		}

		writeStatus("[SORTING] end");

		return files;
	}

	private void mergeSortedFiles(Deque<String> files) throws IOException {

		writeStatus("[MERGING] start");

		int fileCount = 0;

		while (files.size() > 1) {

			writeStatus("files left: " + files.size());

			String name1 = files.removeFirst();
			String name2 = files.removeFirst();
			String newName = files.size() == 0 ? "result" : String.valueOf(fileCount++);

			writeStatus("creating " + newName);
			BufferedWriter mw = createWriter(newName);
			BufferedReader r1 = createReader(name1);
			BufferedReader r2 = createReader(name2);

			String s1 = r1.readLine();
			String s2 = r2.readLine();

			// merging part
			while (s1 != null || s2 != null) {
				if (s1 == null) {
					mw.write(s2);
					s2 = r2.readLine();
				} else if (s2 == null) {
					mw.write(s1);
					s1 = r1.readLine();
				} else {
					int num1 = getInt(s1);
					int num2 = getInt(s2);
					if (num1 < num2) {
						mw.write(s1);
						s1 = r1.readLine();
					} else {
						mw.write(s2);
						s2 = r2.readLine();
					}
				}
				if (s1 != null || s2 != null) mw.newLine();
			}

			r1.close();
			writeStatus("removing " + name1);
			removeFile(name1);

			r2.close();
			writeStatus("removing " + name2);
			removeFile(name2);

			files.addLast(newName);
			mw.close();
		}

		writeStatus("[MERGING] end");
	}

	/**************************************************************************/

	private void writeStatus(String msg) {
		if (DEBUGGER_ON) {
			System.out.println(msg);
		}
	}

	private int getInt(String s) {
		return Integer.parseInt(s);
	}

	private List<String> readFile(String name) throws IOException {
		List<String> ans = new ArrayList<>();
		BufferedReader re = createReader(name);
		String line = re.readLine();
		while (line != null) {
			ans.add(line);
			line = re.readLine();
		}
		return ans;
	}

	private void writeFile(String name, List<String> data) throws IOException {
		writeStatus("creating " + name);
		BufferedWriter wr = createWriter(name);
		for (String s : data) {
			wr.write(s);
			wr.newLine();
		}
		wr.close();
	}

	public int getInputNumberOfItems() {
		return inputNumberOfItems;
	}
}
