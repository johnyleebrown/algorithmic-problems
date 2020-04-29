package util.utility;

import util.utility.reader.InputReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class StringUtils {
	public static String replaceWith(String source, final String[][] strs) {
		for (String[] str : strs) source = source.replaceAll(str[0], str[1]);
		return source;
	}

	public static void main(String[] args) throws IOException {

	}

	private void replaceWordsInFile() throws IOException {
		String source = new String(Files.readAllBytes(Paths.get("test.txt")));
		System.out.println((replaceWith(source, new String[][]{{Pattern.quote("["), "{"}, {Pattern.quote("]"), "}"}})));
	}

	private void readWords() {
		int n = 50;
		InputReader inputReader = new InputReader(System.in);
		String[] lines = new String[n];
        for (int i = 0; i < n; i++) {
            lines[i] = inputReader.nextLine();
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print("\"" + lines[i] + "\"");
            if (i != n - 1) System.out.print(",");
        }
        System.out.println();
    }

    public static String getMaxLen3Strings(String a, String b, String c) {
        return getMaxLen2Strings(a, getMaxLen2Strings(b, c));
    }

    public static String getMaxLen2Strings(String a, String b) {
        if (a.length() > b.length())
            return a;
        else
            return b;
    }

    public static boolean containsSubsequence(String src, String[] a) {
        StringBuilder sb = new StringBuilder();
        for (String s : a) sb.append("(.*)").append(s);
        return src.matches(sb.toString());
    }
}
