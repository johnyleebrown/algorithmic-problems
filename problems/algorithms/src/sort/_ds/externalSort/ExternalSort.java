package sort._ds.externalSort;

import java.io.*;

/**
 * ExternalSort
 */
public class ExternalSort {

	BufferedReader r;
	int limit; // lines per temp file limit

	public ExternalSort(String fileName, int limit) {
		this.limit = limit;
		try {
			r = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	void sort() {

		// split into temp files

		String cur = "";
		int line = 0;
		int fc = 0; //filecount
		int total = 0;

		while (cur != null) {

			BufferedWriter w = null;

			try {

				w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fc++ + "_temp.txt")));

				while (line != limit) {
					cur = r.readLine();
					if (cur == null) break;
					w.write(cur);
					w.newLine();
					line++;
				}

				total += line;
				line = 0;

			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				try {
					w.close();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

		}
		// sort every temp file in mem - quick sort or insertion sort
		// merge every 2 files into new one, remove old 2
		// do that untill there is 1 file left
	}

	public static void main(String[] s) {
		String fileName = "test.txt";
		ExternalSort e = new ExternalSort(fileName, 100);
		e.sort();
	}
}
