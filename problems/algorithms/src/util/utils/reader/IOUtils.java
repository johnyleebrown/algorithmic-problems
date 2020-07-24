package util.utils.reader;

import java.io.*;

public class IOUtils {
	public static BufferedReader createReader(String path, String name) throws FileNotFoundException {
		return new BufferedReader(new InputStreamReader(new FileInputStream(path + name)));
	}

	public static BufferedReader createReader(String name) throws FileNotFoundException {
		return new BufferedReader(new InputStreamReader(new FileInputStream(name)));
	}

	public static BufferedWriter createWriter(String path, String name) throws FileNotFoundException {
		return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + name)));
	}

	public static BufferedWriter createWriter(String name) throws FileNotFoundException {
		return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(name)));
	}

	public static void removeFile(String path, String name) {
		if (!new File(path + name).delete()) {
			throw new RuntimeException("COULDN'T REMOVE FILE.");
		}
	}

	public static void removeFile(String name) {
		if (!new File(name).delete()) {
			throw new RuntimeException("COULDN'T REMOVE FILE.");
		}
	}
}
